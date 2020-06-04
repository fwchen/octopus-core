package com.chenfangwei.octopus.core.domain.project.kanban.column.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "column")
class Column(val name: String, val creatorId: String, val kanbanId: String, val projectId: String) {
    @Id
    lateinit var id: String
}
