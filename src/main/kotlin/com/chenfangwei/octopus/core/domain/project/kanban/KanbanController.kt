package com.chenfangwei.octopus.core.domain.project.kanban

import com.chenfangwei.octopus.core.constant.AuthUserIdKey
import com.chenfangwei.octopus.core.domain.project.kanban.command.CreateKanbanCommand
import com.chenfangwei.octopus.core.domain.project.kanban.model.Kanban
import com.chenfangwei.octopus.core.domain.project.kanban.presenter.KanbanDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
class KanbanController(private val kanbanApplicationService: KanbanApplicationService) {

    @RequestMapping(value = ["/project/{projectId}/kanban"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun createKanban(@RequestBody command: @Valid CreateKanbanCommand, @RequestHeader("X-App-Auth-UserId") userId: String, @PathVariable projectId: String) {
        command.creatorId = userId
        command.projectId = projectId
        kanbanApplicationService.createKanban(command)
    }

    @RequestMapping(value = ["/project/{projectId}/kanbans"], method = [RequestMethod.GET])
    fun queryKanbanList(@RequestHeader(AuthUserIdKey) userId: String, @PathVariable projectId: String): List<KanbanDTO> {
        return kanbanApplicationService.kanbanList(projectId, userId).map{ it-> KanbanDTO(it) }
    }

    @RequestMapping(value = ["/kanban/{kanbanId}"], method = [RequestMethod.GET])
    fun queryKanbanDetail(@RequestHeader(AuthUserIdKey) userId: String, @PathVariable kanbanId: String): KanbanDTO {
        return KanbanDTO(kanbanApplicationService.kanbanDetail(kanbanId, userId))
    }

    @RequestMapping(value = ["/kanbans"], method = [RequestMethod.GET])
    fun queryUserAllKanban(@RequestHeader(AuthUserIdKey) userId: String): List<Kanban> {
        return kanbanApplicationService.userAllKanban(userId)
    }
}