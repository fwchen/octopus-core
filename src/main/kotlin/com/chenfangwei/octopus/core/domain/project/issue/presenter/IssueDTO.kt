package com.chenfangwei.octopus.core.domain.project.issue.presenter

import com.chenfangwei.octopus.core.domain.project.issue.model.Issue

data class IssueDTO constructor(private val issue: Issue) {
    val id = issue.id
    val kanbanId = issue.kanbanId
    val columnId = issue.columnId
    val projectId = issue.projectId
    var creatorId = issue.creatorId
    val title = issue.title
}