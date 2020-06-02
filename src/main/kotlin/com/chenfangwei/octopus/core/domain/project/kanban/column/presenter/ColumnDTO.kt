package com.chenfangwei.octopus.core.domain.project.kanban.column.presenter

import com.chenfangwei.octopus.core.domain.project.kanban.column.model.Column

data class ColumnDTO constructor(private val column: Column) {
    val id = column.id
    val name = column.name
    val creatorId = column.creatorId
}