package com.chenfangwei.octopus.core.domain.project.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "project")
class Project(@Id val id: String, val name: String, val creatorId: String) {
    lateinit var projectSetting: ProjectSetting
}