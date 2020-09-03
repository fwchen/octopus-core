package com.chenfangwei.octopus.core.domain.project.issue.presenter

import com.chenfangwei.octopus.core.domain.project.issue.model.Attachment

data class AttachmentDTO(private val attachment: Attachment) {
    val id = attachment.id
    val fileName = attachment.fileName
    val contentType = attachment.contentType
}