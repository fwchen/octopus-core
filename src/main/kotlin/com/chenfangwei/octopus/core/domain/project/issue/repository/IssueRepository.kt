package com.chenfangwei.octopus.core.domain.project.issue.repository

import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface IssueRepository: MongoRepository<Issue, String> {
    fun findAllByColumnId(columnId: String): List<Issue>
    fun countByProjectId(projectId: String): Int
    fun findAllByProjectId(projectId: String): List<Issue>
    fun findTop1ByProjectIdOrderByOrderDesc(projectId: String): Issue?
    fun findTop10ByKanbanIdOrderByUpdatedAtDesc(kanbanId: String): List<Issue>
    fun findFirstByProjectIdAndOrderLessThanOrderByOrderDesc(projectId: String, order: Float): Issue?
    fun findFirstByProjectIdAndOrderGreaterThanOrderByOrderAsc(projectId: String, order: Float): Issue?
}