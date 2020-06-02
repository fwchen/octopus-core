package com.chenfangwei.octopus.core.domain.project.kanban.column.command

data class CreateColumnCommand(
        val name: String,
        val kanbanId: String,
        val projectId: String) {
    lateinit var creatorId: String
}