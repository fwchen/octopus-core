package com.chenfangwei.octopus.core.domain.project.issue

import com.chenfangwei.octopus.core.domain.project.issue.command.CreateIssueCommand
import com.chenfangwei.octopus.core.domain.project.issue.factory.IssueFactory
import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import com.chenfangwei.octopus.core.domain.project.issue.repository.IssueRepository
import org.springframework.stereotype.Service

@Service
class IssueApplicationService(private val issueRepository: IssueRepository, private val issueFactory: IssueFactory) {

    fun createIssue(command: CreateIssueCommand) {
        val issue = issueFactory.issue(command)
        issueRepository.save(issue)
    }

    fun queryColumnIssues(columnId: String): List<Issue> {
        return issueRepository.findAllByColumnId(columnId)
    }
}