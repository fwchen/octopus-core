package com.chenfangwei.octopus.core.domain.project.kanban.column

import com.chenfangwei.octopus.core.constant.AuthUserIdKey
import com.chenfangwei.octopus.core.domain.project.issue.IssueApplicationService
import com.chenfangwei.octopus.core.domain.project.kanban.KanbanPermissionService
import com.chenfangwei.octopus.core.domain.project.kanban.column.command.CreateColumnCommand
import com.chenfangwei.octopus.core.domain.project.kanban.column.presenter.ColumnDTO
import com.chenfangwei.octopus.core.domain.project.kanban.column.repository.ColumnRepository
import com.chenfangwei.octopus.core.share.exception.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class ColumnController(
        private val columnApplicationService: ColumnApplicationService,
        private val issueApplicationService: IssueApplicationService,
        private val kanbanPermissionService: KanbanPermissionService,
        private val columnRepository: ColumnRepository
) {
    @RequestMapping(value = ["/column"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun createKanban(@RequestBody command: @Valid CreateColumnCommand, @RequestHeader(AuthUserIdKey) userId: String) {
        command.creatorId = userId
        columnApplicationService.createColumn(command)
    }

    @RequestMapping(value = ["/kanban/{kanbanId}/columns"], method = [RequestMethod.GET])
    fun queryColumns(@RequestHeader(AuthUserIdKey) userId: String, @PathVariable kanbanId: String): List<ColumnDTO> {
        kanbanPermissionService.checkCanReadKanban(kanbanId, userId)
        return columnApplicationService.kanbanColumns(kanbanId, userId).map {
            val issues = issueApplicationService.queryColumnIssues(it.id)
            ColumnDTO(it, issues)
        }
    }

    @RequestMapping(value = ["/column/{columnId}"], method = [RequestMethod.DELETE])
    fun deleteColumn(@RequestHeader(AuthUserIdKey) userId: String, @PathVariable columnId: String) {
        val column = columnRepository.findById(columnId).orElseThrow { EntityNotFoundException() }
        kanbanPermissionService.checkCanReadKanban(column.kanbanId, userId)
        columnApplicationService.deleteColumn(columnId)
    }
}