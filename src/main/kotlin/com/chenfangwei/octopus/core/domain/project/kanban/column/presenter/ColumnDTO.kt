package com.chenfangwei.octopus.core.domain.project.kanban.column.presenter

import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import com.chenfangwei.octopus.core.domain.project.issue.presenter.IssueDTO
import com.chenfangwei.octopus.core.domain.project.kanban.column.model.Column

data class ColumnDTO constructor(private val column: Column) {
    val id = column.id
    val name = column.name
    val creatorId = column.creatorId
    val kanbanId = column.kanbanId
    var order = column.order
    var issues: List<IssueDTO>? = null

    constructor(column: Column, issues: List<Issue>): this(column) {
        this.issues = issues.map { IssueDTO(it) }
    }
}