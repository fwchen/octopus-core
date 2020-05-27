package com.chenfangwei.octopus.core.domain.project.kanban.command

data class CreateKanbanCommand(val name: String, val projectId: String, var creatorId: String) {
}