package com.chenfangwei.octopus.core.domain.project.repository

import com.chenfangwei.octopus.core.domain.project.model.Project
import org.springframework.data.mongodb.repository.MongoRepository

interface ProjectRepository: MongoRepository<Project, String> {
    fun findAllByCreatorId(creatorId: String): List<Project>
}