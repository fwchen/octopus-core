package com.chenfangwei.octopus.core.domain.project.kanban.column

import com.chenfangwei.octopus.core.domain.project.kanban.column.command.CreateColumnCommand
import com.chenfangwei.octopus.core.domain.project.kanban.column.model.Column
import com.chenfangwei.octopus.core.domain.project.kanban.column.repository.ColumnRepository
import org.springframework.stereotype.Service

@Service
class ColumnApplicationService(private val columnRepository: ColumnRepository) {
    fun createColumn(command: CreateColumnCommand) {
        val column = Column(command.name, command.creatorId, command.kanbanId, command.projectId)
        this.columnRepository.save(column)
    }

    fun kanbanColumns(kanbanId: String, userId: String): List<Column> {
        return columnRepository.findAllByKanbanId(kanbanId)
    }

    fun deleteColumn(id: String) {
        this.columnRepository.deleteById(id)
    }
}