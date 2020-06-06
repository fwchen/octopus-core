package com.chenfangwei.octopus.core.domain.project.issue.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "issue")
class Issue(@Id val id: String, val projectId: String, val title: String, val creatorId: String) {
    var kanbanId: String? = null
    var columnId: String? = null
}