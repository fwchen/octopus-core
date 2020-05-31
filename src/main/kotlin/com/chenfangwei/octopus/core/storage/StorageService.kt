package com.chenfangwei.octopus.core.storage

import io.minio.PutObjectOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.*

@Service
class StorageService(
        private val minIOService: MinIOService,
        @Value("\${storage.minio.buckets.image}") private val imageBucketName: String) {

    fun saveBase64PngImage(code: String): String {
        val pureCode = code.replace(Regex("^data:image/png;base64,"), "")
        val byteArray: ByteArray = Base64.getDecoder().decode(pureCode)
        val byteArrayInputStream = ByteArrayInputStream(byteArray)
        val toMd5Stream = ByteArrayInputStream(byteArray)
        val options = PutObjectOptions(byteArrayInputStream.available().toLong(), -1)
        options.setContentType("image/png")
        val objectName: String = DigestUtils.md5DigestAsHex(toMd5Stream).toUpperCase()
        minIOService.getMinioClient().putObject(imageBucketName, objectName, byteArrayInputStream, options)
        byteArrayInputStream.close()
        toMd5Stream.close()
        return "object://$objectName"
    }

    fun receiveObject(objectId: String): InputStream? {
        return minIOService.getMinioClient().getObject(imageBucketName, objectId)
    }
}