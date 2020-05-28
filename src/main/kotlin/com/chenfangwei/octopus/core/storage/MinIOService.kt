package com.chenfangwei.octopus.core.storage

import io.minio.MinioClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MinIOService {
    @Value("{storage.minio.endpoint}")
    private val endpoint: String? = null

    @Value("{storage.minio.accesskey}")
    private val accessKey: String? = null

    @Value("{storage.minio.secretkey}")
    private val secretKey: String? = null

    private val minioClient: MinioClient

    init {
        minioClient = MinioClient(endpoint, accessKey, secretKey)
    }

    public fun getMinioClient(): MinioClient {
        return minioClient
    }

}