package com.chenfangwei.octopus.core.domain.project.issue.model

import com.chenfangwei.octopus.core.share.factory.generateId
import org.springframework.data.annotation.CreatedDate

import java.util.*

class Attachment(val objectId: String, val fileName: String?, val contentType: String?, val creatorId: String) {
    var id: String = generateId()

    @CreatedDate
    var createdAt: Date? = null

    companion object {
        fun create( objectId: String,  fileName: String?, contentType: String?,  creatorId: String): Attachment {
            val attachment = Attachment(objectId, fileName, contentType, creatorId)
            attachment.createdAt = Date()
            return attachment
        }
    }
}