package com.chenfangwei.octopus.core.domain.project

import com.chenfangwei.octopus.core.constant.AuthUserIdKey
import com.chenfangwei.octopus.core.domain.project.command.CreateProjectCommand
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class ProjectController(private val projectApplicationService: ProjectApplicationService) {

    @RequestMapping(value = ["/project"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun createProject(@RequestBody command: @Valid CreateProjectCommand, @RequestHeader(AuthUserIdKey) userId: String) {
        command.creatorId = userId
        projectApplicationService.createProject(command)
    }

    @RequestMapping(value = ["/projects"], method = [RequestMethod.GET])
    fun queryProjectList(@RequestHeader(AuthUserIdKey) userId: String) {
        projectApplicationService.queryProjectList(userId)
    }

}