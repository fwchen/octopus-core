package com.chenfangwei.octopus.core.domain.project.kanban.command

data class CreateKanbanCommand(val name: String) {
    lateinit var projectId: String
    lateinit var creatorId: String
}