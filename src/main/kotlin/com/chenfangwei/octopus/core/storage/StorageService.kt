package com.chenfangwei.octopus.core.storage

import com.chenfangwei.octopus.core.config.StorageBucketConfig
import com.chenfangwei.octopus.core.share.factory.generateId
import io.minio.PutObjectArgs
import io.minio.PutObjectOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.*


@Service
class StorageService(
        private val minIOService: MinIOService,
        private val storageBucketConfig: StorageBucketConfig,
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

    fun saveFile(file: MultipartFile) {
        val options = PutObjectOptions(file.size, -1)
        val objectName = generateId()
        val userMetadata: Map<String, String> = HashMap()
        minIOService.getMinioClient().putObject(PutObjectArgs.builder().bucket(storageBucketConfig.fileBucketName).`object`(objectName).stream(
                file.inputStream, file.size, -1)
                .userMetadata(userMetadata)
                .build())

    }

    fun receiveObject(objectId: String): InputStream? {
        return minIOService.getMinioClient().getObject(imageBucketName, objectId)
    }
}