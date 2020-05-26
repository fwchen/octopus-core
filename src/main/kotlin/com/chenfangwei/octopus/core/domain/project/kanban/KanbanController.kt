package com.chenfangwei.octopus.core.domain.project.kanban

import com.chenfangwei.octopus.core.domain.project.kanban.command.CreateKanbanCommand
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class KanbanController(private val kanbanApplicationService: KanbanApplicationService) {

    @RequestMapping(value = ["/kanban"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun createKanban(@RequestBody command: @Valid CreateKanbanCommand, @RequestHeader("X-App-Auth-UserId") userId: Long) {
        command.creatorId = userId
        kanbanApplicationService.createKanban(command)
    }
}