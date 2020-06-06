package com.chenfangwei.octopus.core.domain.project.issue

import com.chenfangwei.octopus.core.constant.AuthUserIdKey
import com.chenfangwei.octopus.core.domain.project.issue.command.CreateIssueCommand
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class IssueController(private val issueApplicationService: IssueApplicationService) {

    @RequestMapping(value = ["/issue"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun createIssue(@RequestBody command: @Valid CreateIssueCommand, @RequestHeader(AuthUserIdKey) userId: String) {
        command.userId = userId
        issueApplicationService.createIssue(command)
    }

}