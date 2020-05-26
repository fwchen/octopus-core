package com.chenfangwei.octopus.core.domain.project.kanban

import com.chenfangwei.octopus.core.domain.project.kanban.command.CreateKanbanCommand
import com.chenfangwei.octopus.core.domain.project.kanban.model.Kanban
import com.chenfangwei.octopus.core.domain.project.kanban.repository.KanbanRepository
import org.springframework.stereotype.Service

@Service
class KanbanApplicationService(private val kanbanRepository: KanbanRepository) {

    fun createKanban(command: CreateKanbanCommand) {
        val kanban = Kanban(command.name, command.creatorId, command.projectId)
        kanbanRepository.save(kanban)
    }
}