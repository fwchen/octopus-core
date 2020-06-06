package com.chenfangwei.octopus.core.domain.project.issue.factory

import com.chenfangwei.octopus.core.domain.project.issue.command.CreateIssueCommand
import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import com.chenfangwei.octopus.core.domain.project.issue.repository.IssueRepository
import org.springframework.stereotype.Component

@Component
class IssueFactory(private val issueRepository: IssueRepository) {

    fun issue(command: CreateIssueCommand): Issue {
        val id = command.projectId + "-" + issueRepository.countByProjectId(command.projectId)
        val issue = Issue(id, command.projectId, command.title, command.userId)
        issue.columnId = command.columnId
        issue.kanbanId = command.kanbanId
        return issue
    }
}