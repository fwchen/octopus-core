package com.chenfangwei.octopus.core.domain.project.kanban

import com.chenfangwei.octopus.core.domain.project.kanban.repository.KanbanRepository
import com.chenfangwei.octopus.core.share.exception.EntityNotFoundException
import com.chenfangwei.octopus.core.share.exception.ResourcePermissionException
import org.springframework.stereotype.Service

@Service
class KanbanPermissionService(private val kanbanRepository: KanbanRepository) {
    fun canReadKanban(kanbanId: String, userId: String): Boolean {
        val kanban = kanbanRepository.findById(kanbanId).orElseThrow { EntityNotFoundException() }
        return kanban.creatorId === userId
    }

    fun checkCanReadKanban(kanbanId: String, userId: String) {
        if (!canReadKanban(kanbanId, userId)) {
            throw ResourcePermissionException()
        }
    }
}