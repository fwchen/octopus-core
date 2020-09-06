package com.chenfangwei.octopus.core.domain.project.issue.model

import com.chenfangwei.octopus.core.share.factory.generateId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "issue")
class Issue(@Id val id: String, val projectId: String, var title: String, val creatorId: String) {
    var kanbanId: String? = null
    var columnId: String? = null
    var desc: String = ""
    var order: Float = 0F
    var assigneeId: String? = null
    var deadline: Date? = null
    var deadlineDone: Boolean? = null
    var comments: List<Comment> = mutableListOf()
    var attachments: List<Attachment> = mutableListOf()
    var removed: Boolean = false

    @Version
    var version: Long? = null // left @EnableMongoAuditing enabled.


    @CreatedDate
    var createdAt: Date? = null

    @LastModifiedDate
    var updatedAt: Date? = null

    fun initOrderByMaxIssue(maxOrderIssue: Issue?) {
        if (maxOrderIssue == null) {
            this.order = 0F
        } else {
            this.order = maxOrderIssue.order + 100F
        }
    }

    fun addComment(creatorId: String, content: String) {
        comments = comments + Comment.create(generateId(), id, content, creatorId)
    }

    fun removeComment(commentId: String) {
        comments = comments.dropWhile { comment -> comment.getId() == commentId }
    }

    fun addAttachment(objectId: String, fileName: String?, contentType: String?, creatorId: String) {
        attachments = attachments + Attachment.create(objectId, fileName, contentType, creatorId)
    }

    fun findAttachment(attachmentId: String): Attachment {
        return attachments.find { attachment -> attachment.id == attachmentId }!!
    }

    fun removeAttachment(attachmentId: String) {
        attachments = attachments.dropWhile { attachment -> attachment.id == attachmentId }
    }

    fun remove() {
        removed = true
    }
}