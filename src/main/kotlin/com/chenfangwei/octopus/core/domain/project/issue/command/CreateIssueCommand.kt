package com.chenfangwei.octopus.core.domain.project.issue.command

data class CreateIssueCommand(val title: String, val projectId: String) {
    lateinit var userId: String
    var kanbanId: String? = null
    var columnId: String? = null
}