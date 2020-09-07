package com.chenfangwei.octopus.core.domain.project.epic.repository

import com.chenfangwei.octopus.core.domain.project.epic.model.Epic
import org.springframework.data.mongodb.repository.MongoRepository

interface EpicRepository: MongoRepository<Epic, String> {
    fun findAllByProjectId(projectId: String): List<Epic>
}