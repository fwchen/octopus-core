package com.chenfangwei.octopus.core.domain.project.issue.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "issue")
class Issue(@Id val id: String, val projectId: String, var title: String, val creatorId: String) {
    var kanbanId: String? = null
    var columnId: String? = null
    var desc: String = ""
    var order: Float = 0F
    var assigneeId: String? = null
    var deadline: Date? = null
    var deadlineDone: Boolean? = null

    @CreatedDate
    var createdAt: Date? = null

    @LastModifiedDate
    var updatedAt: Date? = null

    fun initOrderByMaxIssue(maxOrderIssue: Issue?) {
        if (maxOrderIssue == null) {
            this.order = 0F
        } else {
            this.order = maxOrderIssue.order + 100F
        }
    }
}