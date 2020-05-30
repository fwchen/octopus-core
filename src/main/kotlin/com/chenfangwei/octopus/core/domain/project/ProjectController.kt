package com.chenfangwei.octopus.core.domain.project

import com.chenfangwei.octopus.core.constant.AuthUserIdKey
import com.chenfangwei.octopus.core.domain.project.command.CreateProjectCommand
import com.chenfangwei.octopus.core.domain.project.command.SetProjectCoverCommand
import com.chenfangwei.octopus.core.domain.project.model.Project
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
    fun queryProjectList(@RequestHeader(AuthUserIdKey) userId: String): List<Project> {
        return projectApplicationService.queryProjectList(userId)
    }

    @RequestMapping(value = ["/project/cover"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun setProjectCover(@RequestBody command: @Valid SetProjectCoverCommand, @RequestHeader(AuthUserIdKey) userId: String) {
        command.userId = userId
        projectApplicationService.setProjectCover(command)
    }
}