package com.chenfangwei.octopus.core.domain.project.issue.presenter

import com.chenfangwei.octopus.core.domain.project.issue.model.Issue

class IssueDetailDTO constructor(issue: Issue) {
    val id = issue.id
    val kanbanId = issue.kanbanId
    val desc = issue.desc
    val columnId = issue.columnId
    val projectId = issue.projectId
    val creatorId = issue.creatorId
    val deadline = issue.deadline
    val startTime = issue.startTime
    val deadlineDone = issue.deadlineDone
    val title = issue.title
    val assigneeId = issue.assigneeId
    val order = issue.order
    val comments = issue.comments
    val attachments = issue.attachments.map { attachment -> AttachmentDTO(attachment) }
}