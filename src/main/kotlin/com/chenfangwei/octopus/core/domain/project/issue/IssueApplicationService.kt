package com.chenfangwei.octopus.core.domain.project.issue

import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import com.chenfangwei.octopus.core.domain.project.issue.repository.IssueRepository
import org.springframework.stereotype.Service

@Service
class IssueApplicationService(private val issueRepository: IssueRepository) {

    fun createIssue() {}

    fun queryColumnIssues(columnId: String): List<Issue> {
        return issueRepository.findAllByColumnId(columnId)
    }
}