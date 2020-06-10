package com.chenfangwei.octopus.core.domain.project.issue.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "issue")
class Issue(@Id val id: String, val projectId: String, var title: String, val creatorId: String) {
    var kanbanId: String? = null
    var columnId: String? = null
    var desc: String = ""
    var assigneeId: String? = null
    var deadline: Date? = null
    var deadlineDone: Boolean? = null
}