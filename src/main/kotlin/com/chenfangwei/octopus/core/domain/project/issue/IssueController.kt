package com.chenfangwei.octopus.core.domain.project.issue

import com.chenfangwei.octopus.core.constant.AuthUserIdKey
import com.chenfangwei.octopus.core.domain.project.issue.command.CreateIssueCommand
import com.chenfangwei.octopus.core.domain.project.issue.command.UpdateIssueCommand
import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import com.chenfangwei.octopus.core.share.exception.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class IssueController(
        private val issueApplicationService: IssueApplicationService,
        private val issuePermissionService: IssuePermissionService) {

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

    @RequestMapping(value = ["/issue/{issueId}"], method = [RequestMethod.PATCH])
    fun updateIssueDetail(@RequestBody command: @Valid UpdateIssueCommand, @RequestHeader(AuthUserIdKey) userId: String, @PathVariable issueId: String) {
        if (command.id != issueId) {
            throw BadRequestException()
        }
        command.userId = userId
        issuePermissionService.guardOperationIssue(issueId, userId)
        issueApplicationService.updateIssue(command)
    }
}