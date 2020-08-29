package com.chenfangwei.octopus.core.domain.project.issue.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
class Comment(
        @Id private val id: String,
        val issueId: String,
        var content: String,
        var creatorId: String) {

    @Version
    private val version: Long? = null

    @CreatedDate
    var createdAt: Date? = null

    @LastModifiedDate
    var updatedAt: Date? = null

    fun getId(): String {
        return id
    }
}