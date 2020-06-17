package com.chenfangwei.octopus.core.domain.project.issue

import com.chenfangwei.octopus.core.constant.AuthUserIdKey
import com.chenfangwei.octopus.core.domain.project.ProjectPermissionService
import com.chenfangwei.octopus.core.domain.project.issue.command.CreateIssueCommand
import com.chenfangwei.octopus.core.domain.project.issue.command.RankIssueCommand
import com.chenfangwei.octopus.core.domain.project.issue.command.UpdateIssueCommand
import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import com.chenfangwei.octopus.core.domain.project.issue.presenter.IssueChangedOrderDTO
import com.chenfangwei.octopus.core.domain.project.issue.presenter.IssueDTO
import com.chenfangwei.octopus.core.domain.project.kanban.KanbanPermissionService
import com.chenfangwei.octopus.core.domain.project.kanban.column.presenter.ColumnDTO
import com.chenfangwei.octopus.core.share.exception.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
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
    fun queryIssueDetail(@RequestHeader(AuthUserIdKey) userId: String, @PathVariable issueId: String): Issue {
        return issueApplicationService.queryIssueDetail(issueId)
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
}