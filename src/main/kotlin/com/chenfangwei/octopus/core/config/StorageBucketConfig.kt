package com.chenfangwei.octopus.core.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class StorageBucketConfig(
        @Value("\${storage.minio.buckets.file}") val fileBucketName: String
)