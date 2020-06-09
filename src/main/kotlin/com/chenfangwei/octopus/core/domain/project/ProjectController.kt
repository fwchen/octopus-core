package com.chenfangwei.octopus.core.domain.project

import com.chenfangwei.octopus.core.constant.AuthUserIdKey
import com.chenfangwei.octopus.core.domain.project.command.CreateProjectCommand
import com.chenfangwei.octopus.core.domain.project.command.SetProjectCoverCommand
import com.chenfangwei.octopus.core.domain.project.presenter.ProjectDTO
import com.chenfangwei.octopus.core.share.model.Account
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.util.*
import javax.validation.Valid


@RestController
class ProjectController(private val projectApplicationService: ProjectApplicationService, restTemplateBuilder: RestTemplateBuilder) {
    private val restTemplate: RestTemplate = restTemplateBuilder.build()


    @Value("\${service.account}")
    private lateinit var accountServiceUrl: String

    @RequestMapping(value = ["/project"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun createProject(@RequestBody command: @Valid CreateProjectCommand, @RequestHeader(AuthUserIdKey) userId: String) {
        command.creatorId = userId
        projectApplicationService.createProject(command)
    }

    @RequestMapping(value = ["/project/{projectId}"], method = [RequestMethod.GET])
    fun queryProjectDetail(@PathVariable projectId: String, @RequestHeader(AuthUserIdKey) userId: String): ProjectDTO {
        return ProjectDTO(projectApplicationService.projectDetail(projectId, userId))
    }

    @RequestMapping(value = ["/projects"], method = [RequestMethod.GET])
    fun queryProjectList(@RequestHeader(AuthUserIdKey) userId: String): List<ProjectDTO> {
        return projectApplicationService.projectList(userId).map { it -> ProjectDTO(it) }
    }

    @RequestMapping(value = ["/project/{projectId}/cover"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun setProjectCover(@RequestBody command: @Valid SetProjectCoverCommand, @RequestHeader(AuthUserIdKey) userId: String, @PathVariable projectId: String) {
        command.userId = userId
        projectApplicationService.setProjectCover(projectId, command)
    }

    @RequestMapping(value = ["/project/{projectId}/participants"], method = [RequestMethod.GET])
    fun projectParticipants(@RequestHeader(AuthUserIdKey) userId: String, @PathVariable projectId: String): List<Account> {
        val url = "$accountServiceUrl/accounts"
        return restTemplate.getForObject(url, Array<Account>::class.java)!!.toList()
    }
}