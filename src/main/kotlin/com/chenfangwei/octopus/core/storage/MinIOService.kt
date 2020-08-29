package com.chenfangwei.octopus.core.storage

import io.minio.MinioClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MinIOService(@Value("\${storage.minio.accesskey}") accessKey: String, @Value("\${storage.minio.endpoint}") endpoint: String, @Value("\${storage.minio.secretkey}") secretKey: String, @Value("\${storage.minio.buckets.image}") imageBucketName: String) {
    private val minioClient: MinioClient = MinioClient(endpoint, accessKey, secretKey)

    init {
        checkBucketExistOrCreate(imageBucketName)
    }

    fun getMinioClient(): MinioClient {
        return minioClient
    }

    private fun checkBucketExistOrCreate(bucket: String) {
        val isExist = minioClient.bucketExists(bucket)
        if (!isExist) {
            minioClient.makeBucket(bucket)
        }
    }
}