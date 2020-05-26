package com.chenfangwei.octopus.core.domain.project.kanban.command

data class CreateKanbanCommand(val name: String, var creatorId: Long){
    lateinit var projectId: String;
}