package com.chenfangwei.octopus.core.domain.project.kanban.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "kanban")
class Kanban(val name: String, val creatorId: Long, val projectId: String) {
    @Id
    lateinit var id: String;
}