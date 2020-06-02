package com.chenfangwei.octopus.core.domain.project.issue.model

import org.springframework.data.annotation.Id

class Issue(@Id val id: String, val projectId: String, var title: String) {
    var kanbanId: String? = null
    var columnId: String? = null
}