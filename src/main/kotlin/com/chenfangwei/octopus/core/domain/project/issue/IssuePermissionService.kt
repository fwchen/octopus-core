package com.chenfangwei.octopus.core.domain.project.issue

import com.chenfangwei.octopus.core.domain.project.ProjectPermissionService
import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import com.chenfangwei.octopus.core.domain.project.issue.repository.IssueRepository
import com.chenfangwei.octopus.core.domain.project.model.Project
import com.chenfangwei.octopus.core.share.exception.EntityNotFoundException
import com.chenfangwei.octopus.core.share.exception.ResourcePermissionException
import org.springframework.stereotype.Service

@Service
class IssuePermissionService(
        private val issueRepository: IssueRepository,
        private val projectPermissionService: ProjectPermissionService
) {
    fun canOperateIssue(issue: Issue, userId: String): Boolean {
        return projectPermissionService.canOperateProject(issue.projectId, userId)
    }

    fun canOperateIssue(issueId: String, userId: String): Boolean {
        val issue = issueRepository.findById(issueId).orElseThrow { EntityNotFoundException() }
        return canOperateIssue(issue, userId)
    }

    fun guardOperationIssue(issueId: String, userId: String) {
        if (!this.canOperateIssue(issueId, userId)) {
            throw ResourcePermissionException()
        }
    }
}