package com.chenfangwei.octopus.core.domain.project.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "project")
class Project(@Id val id: String, var name: String, val creatorId: String) {
    lateinit var projectSetting: ProjectSetting

    var coverUri: String = ""

    @CreatedDate
    var createdAt: Date = Date()

    @LastModifiedDate
    var updatedAt: Date = Date()
}