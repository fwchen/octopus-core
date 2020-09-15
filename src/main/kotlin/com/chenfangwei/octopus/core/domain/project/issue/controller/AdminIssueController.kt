package com.chenfangwei.octopus.core.domain.project.issue.controller

import com.chenfangwei.octopus.core.constant.AuthUserIdKey
import com.chenfangwei.octopus.core.constant.AuthUserRole
import com.chenfangwei.octopus.core.domain.project.issue.command.CreateCommentCommand
import com.chenfangwei.octopus.core.share.enum.UserRole
import com.chenfangwei.octopus.core.share.exception.ForbiddenRequestException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class AdminIssueController {

    @RequestMapping(value = ["/issue/update-all"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun updateAllIssues(@Valid @RequestBody command: CreateCommentCommand,
                      @RequestHeader(AuthUserIdKey) userId: String,
                      @RequestHeader(AuthUserRole) userRole: String,
                      @PathVariable issueId: String) {
        if (UserRole.valueOf(userRole) != UserRole.Admin) {
            throw ForbiddenRequestException()
        }
    }
}