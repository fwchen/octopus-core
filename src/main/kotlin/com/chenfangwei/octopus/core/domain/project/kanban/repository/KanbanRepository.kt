package com.chenfangwei.octopus.core.domain.project.kanban.repository

import com.chenfangwei.octopus.core.domain.project.kanban.model.Kanban
import org.springframework.data.mongodb.repository.MongoRepository

interface KanbanRepository: MongoRepository<Kanban, String> {}