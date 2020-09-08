package com.chenfangwei.octopus.core.domain.project.issue.controller

import com.chenfangwei.octopus.core.constant.AuthUserIdKey
import com.chenfangwei.octopus.core.domain.project.issue.IssueApplicationService
import com.chenfangwei.octopus.core.domain.project.issue.IssuePermissionService
import com.chenfangwei.octopus.core.domain.project.issue.command.CreateCommentCommand
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class CommentController(
        private val issueApplicationService: IssueApplicationService,
        private val issuePermissionService: IssuePermissionService
) {

    @RequestMapping(value = ["/issue/{issueId}/comment"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun createComment(@Valid @RequestBody command: CreateCommentCommand,
                      @RequestHeader(AuthUserIdKey) userId: String,
                      @PathVariable issueId: String) {
        command.userId = userId
        issuePermissionService.guardOperationIssue(issueId, userId)
        issueApplicationService.createComment(command)
    }


    @RequestMapping(value = ["/issue/{issueId}/comment/{commentId}"], method = [RequestMethod.DELETE])
    fun deleteComment(@RequestHeader(AuthUserIdKey) userId: String,
                      @PathVariable issueId: String,
                      @PathVariable commentId: String) {
        issuePermissionService.guardOperationIssue(issueId, userId)
        issueApplicationService.deleteComment(issueId, commentId)
    }
}