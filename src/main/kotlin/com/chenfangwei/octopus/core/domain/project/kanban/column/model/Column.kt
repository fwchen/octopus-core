package com.chenfangwei.octopus.core.domain.project.kanban.column.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "kanban")
class Column(val name: String, val creatorId: Long, val kanbanId: String, val projectId: String) {
    @Id
    lateinit var id: String;
}
