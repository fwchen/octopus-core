package com.chenfangwei.octopus.core.domain.project.kanban.column.repository

import com.chenfangwei.octopus.core.domain.project.kanban.column.model.Column
import org.springframework.data.mongodb.repository.MongoRepository

interface ColumnRepository: MongoRepository<Column, String> {
}