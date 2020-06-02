package com.chenfangwei.octopus.core.domain.project.kanban.column

import com.chenfangwei.octopus.core.domain.project.kanban.KanbanPermissionService
import com.chenfangwei.octopus.core.domain.project.kanban.column.command.CreateColumnCommand
import com.chenfangwei.octopus.core.domain.project.kanban.column.model.Column
import com.chenfangwei.octopus.core.domain.project.kanban.column.repository.ColumnRepository
import org.springframework.stereotype.Service
import javax.validation.Valid

@Service
class ColumnApplicationService(private val columnRepository: ColumnRepository, private  val kanbanPermissionService: KanbanPermissionService) {
    fun createColumn(command: @Valid CreateColumnCommand) {
        val column = Column(command.name, command.creatorId, command.kanbanId, command.projectId)
        this.columnRepository.save(column)
    }

    fun kanbanColumns(kanbanId: String, userId: String): List<Column> {
        kanbanPermissionService.checkCanReadKanban(kanbanId, userId)
        return columnRepository.findAllByKanbanId(kanbanId)
    }
}