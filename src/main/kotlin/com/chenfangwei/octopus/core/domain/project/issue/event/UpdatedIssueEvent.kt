package com.chenfangwei.octopus.core.domain.project.issue.event

import com.chenfangwei.octopus.core.domain.project.issue.model.Issue

data class UpdatedIssueEvent(private val issue: Issue) {
    val id = issue.id
    val kanbanId = issue.kanbanId
    val columnId = issue.columnId
    val projectId = issue.projectId
    var creatorId = issue.creatorId
    var startTime = issue.startTime
    val deadline = issue.deadline
    val title = issue.title
    var assigneeId = issue.assigneeId
    var order = issue.order
}