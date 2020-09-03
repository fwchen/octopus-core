package com.chenfangwei.octopus.core.domain.project.issue.controller

import com.chenfangwei.octopus.core.constant.AuthUserIdKey
import com.chenfangwei.octopus.core.domain.project.ProjectPermissionService
import com.chenfangwei.octopus.core.domain.project.issue.IssueApplicationRankService
import com.chenfangwei.octopus.core.domain.project.issue.IssueApplicationService
import com.chenfangwei.octopus.core.domain.project.issue.IssuePermissionService
import com.chenfangwei.octopus.core.domain.project.issue.command.CreateIssueCommand
import com.chenfangwei.octopus.core.domain.project.issue.command.RankIssueCommand
import com.chenfangwei.octopus.core.domain.project.issue.command.UpdateIssueCommand
import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import com.chenfangwei.octopus.core.domain.project.issue.presenter.IssueChangedOrderDTO
import com.chenfangwei.octopus.core.domain.project.issue.presenter.IssueDTO
import com.chenfangwei.octopus.core.domain.project.issue.presenter.IssueDetailDTO
import com.chenfangwei.octopus.core.share.exception.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.validation.Valid

@RestController
class IssueController(
        private val issueApplicationService: IssueApplicationService,
        private val issuePermissionService: IssuePermissionService,
        private val projectPermissionService: ProjectPermissionService,
        private val issueApplicationRankService: IssueApplicationRankService) {

    @RequestMapping(value = ["/issue"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun createIssue(@RequestBody command: @Valid CreateIssueCommand, @RequestHeader(AuthUserIdKey) userId: String): String {
        command.userId = userId
        return issueApplicationService.createIssue(command)
    }

    @RequestMapping(value = ["/issue/{issueId}"], method = [RequestMethod.GET])
    fun queryIssueDetail(@RequestHeader(AuthUserIdKey) userId: String, @PathVariable issueId: String): IssueDetailDTO {
        return IssueDetailDTO(issueApplicationService.queryIssueDetail(issueId))
    }

    @RequestMapping(value = ["/project/{projectId}/issues"], method = [RequestMethod.GET])
    fun queryProjectIssues(@RequestHeader(AuthUserIdKey) userId: String, @PathVariable projectId: String): List<Issue> {
        projectPermissionService.guardOperationProject(projectId, userId)
        return issueApplicationService.queryProjectIssues(projectId)
    }

    @RequestMapping(value = ["/issue/{issueId}"], method = [RequestMethod.PATCH])
    fun updateIssueDetail(@RequestBody command: @Valid UpdateIssueCommand, @RequestHeader(AuthUserIdKey) userId: String, @PathVariable issueId: String) {
        if (command.id != issueId) {
            throw BadRequestException()
        }
        command.userId = userId
        issuePermissionService.guardOperationIssue(issueId, userId)
        issueApplicationService.updateIssue(command)
    }

    @RequestMapping(value = ["/kanban/{kanbanId}/recently-issues"], method = [RequestMethod.GET])
    fun queryRecentlyIssues(@RequestHeader(AuthUserIdKey) userId: String, @PathVariable kanbanId: String): List<IssueDTO> {
        return issueApplicationService.kanbanRecentlyIssues(kanbanId).map { IssueDTO(it) }
    }

    @RequestMapping(value = ["/project/{projectId}/rank-issue"], method = [RequestMethod.POST])
    fun rankIssue(@RequestBody command: RankIssueCommand, @RequestHeader(AuthUserIdKey) userId: String, @PathVariable projectId: String): List<IssueChangedOrderDTO> {
        projectPermissionService.guardOperationProject(projectId, userId)
        val changedIssues = issueApplicationRankService.rankIssue(command.issueId, command.targetIssueId, command.isBefore)
        return changedIssues.map { IssueChangedOrderDTO(it) }
    }

    @RequestMapping(value = ["/issue/{issueId}"], method = [RequestMethod.DELETE])
    fun removeIssue(@PathVariable issueId: String, @RequestHeader(AuthUserIdKey) userId: String) {
        issuePermissionService.guardOperationIssue(issueId, userId)
        issueApplicationService.removeIssue(issueId)
    }

    @PostMapping(value = ["/issue/{issueId}/attachment"])
    fun uploadAttachment(@PathVariable issueId: String, @RequestHeader(AuthUserIdKey) userId: String, @RequestParam file: MultipartFile) {
        issueApplicationService.saveAttachment(issueId, userId, file)
    }

    @GetMapping(value = ["/issue/{issueId}/attachment/{attachmentId}"], produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun getAttachment(@PathVariable issueId: String, @RequestHeader(AuthUserIdKey) userId: String, @PathVariable attachmentId: String): ByteArray {
        issuePermissionService.guardOperationIssue(issueId, userId)
        return issueApplicationService.getAttachment(issueId, attachmentId).toByteArray()
    }
}