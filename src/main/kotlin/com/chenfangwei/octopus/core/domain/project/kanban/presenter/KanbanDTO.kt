package com.chenfangwei.octopus.core.domain.project.kanban.presenter

import com.chenfangwei.octopus.core.domain.project.kanban.model.Kanban

data class KanbanDTO constructor(private val kanban: Kanban) {
    val id = kanban.id
    val name = kanban.name
}