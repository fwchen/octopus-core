package com.chenfangwei.octopus.core.domain.project.issue.presenter

import com.chenfangwei.octopus.core.domain.project.issue.model.Issue

class IssueDetailDTO constructor(issue: Issue) {
    val id = issue.id
    val kanbanId = issue.kanbanId
    var desc = issue.desc
    val columnId = issue.columnId
    val projectId = issue.projectId
    var creatorId = issue.creatorId
    val title = issue.title
    var assigneeId = issue.assigneeId
    var order = issue.order
    var comments = issue.comments
    var attachments = issue.attachments.map { attachment -> AttachmentDTO(attachment) }
}