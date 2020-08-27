package com.chenfangwei.octopus.core.domain.project.issue.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import java.util.*

class Comment(
        @Id val id: String,
        val issueId: String,
        var content: String,
        var creatorId: String) {


    @CreatedDate
    var createdAt: Date? = null

    @LastModifiedDate
    var updatedAt: Date? = null
}