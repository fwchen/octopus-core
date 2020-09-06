package com.chenfangwei.octopus.core.domain.project.issue.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import java.util.*

class Comment(
        @Id private val id: String,
        val issueId: String,
        var content: String,
        var creatorId: String) {

    companion object {
        fun create(id: String, issueId: String, content: String, creatorId: String): Comment {
            val comment = Comment(id, issueId, content, creatorId)
            comment.createdAt = Date()
            return comment
        }
    }

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