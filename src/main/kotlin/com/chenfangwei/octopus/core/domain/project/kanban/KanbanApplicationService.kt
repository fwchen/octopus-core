package com.chenfangwei.octopus.core.domain.project.kanban

import com.chenfangwei.octopus.core.domain.project.ProjectApplicationService
import com.chenfangwei.octopus.core.domain.project.ProjectPermissionService
import com.chenfangwei.octopus.core.domain.project.kanban.command.CreateKanbanCommand
import com.chenfangwei.octopus.core.domain.project.kanban.model.Kanban
import com.chenfangwei.octopus.core.domain.project.kanban.repository.KanbanRepository
import com.chenfangwei.octopus.core.share.exception.ResourcePermissionException
import org.springframework.stereotype.Service

@Service
class KanbanApplicationService(private val kanbanRepository: KanbanRepository,
                               private val projectPermissionService: ProjectPermissionService) {

    fun createKanban(command: CreateKanbanCommand) {
        val kanban = Kanban(command.name, command.creatorId, command.projectId)
        kanbanRepository.save(kanban)
    }

    fun kanbanList(projectId: String, userId: String): List<Kanban> {
        if (!projectPermissionService.canOperateProject(projectId, userId)) {
            throw ResourcePermissionException()
        }
        return kanbanRepository.findAllByProjectId(projectId)
    }
}