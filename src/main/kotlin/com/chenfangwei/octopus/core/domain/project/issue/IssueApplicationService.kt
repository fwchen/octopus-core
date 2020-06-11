package com.chenfangwei.octopus.core.domain.project.issue

import com.chenfangwei.octopus.core.domain.project.issue.command.CreateIssueCommand
import com.chenfangwei.octopus.core.domain.project.issue.command.UpdateIssueCommand
import com.chenfangwei.octopus.core.domain.project.issue.factory.IssueFactory
import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import com.chenfangwei.octopus.core.domain.project.issue.repository.IssueRepository
import com.chenfangwei.octopus.core.share.exception.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class IssueApplicationService(private val issueRepository: IssueRepository, private val issueFactory: IssueFactory) {

    fun createIssue(command: CreateIssueCommand): String {
        val issue = issueFactory.issue(command)
        issueRepository.save(issue)
        return issue.id
    }

    fun queryColumnIssues(columnId: String): List<Issue> {
        return issueRepository.findAllByColumnId(columnId)
    }

    fun queryIssueDetail(issueId: String): Issue {
        return issueRepository.findById(issueId).orElseThrow{ EntityNotFoundException() }
    }

    fun updateIssue(command: UpdateIssueCommand) {
        val issue = issueRepository.findById(command.id).orElseThrow { EntityNotFoundException() }
        command.copyToIssue(issue)
        issueRepository.save(issue)
    }

    fun queryProjectIssues(projectId: String): List<Issue> {
        return issueRepository.findAllByProjectId(projectId)
    }
}