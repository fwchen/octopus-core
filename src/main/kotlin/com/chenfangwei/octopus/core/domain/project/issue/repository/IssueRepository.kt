package com.chenfangwei.octopus.core.domain.project.issue.repository

import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import org.springframework.data.mongodb.repository.MongoRepository

interface IssueRepository: MongoRepository<Issue, String> {
}