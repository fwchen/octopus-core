package com.chenfangwei.octopus.core.domain.project.kanban.column

import com.chenfangwei.octopus.core.domain.project.kanban.column.command.CreateColumnCommand
import com.chenfangwei.octopus.core.domain.project.kanban.command.CreateKanbanCommand
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class ColumnController(private val columnApplicationService: ColumnApplicationService) {

    @RequestMapping(value = ["/column"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun createKanban(@RequestBody command: @Valid CreateColumnCommand, @RequestHeader("X-App-Auth-UserId") userId: String) {
        command.creatorId = userId
        columnApplicationService.createColumn(command)
    }
}