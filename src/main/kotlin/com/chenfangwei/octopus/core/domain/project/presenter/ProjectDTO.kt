package com.chenfangwei.octopus.core.domain.project.presenter

import com.chenfangwei.octopus.core.domain.project.model.Project

data class ProjectDTO constructor(private val project: Project) {
    val id = project.id
    val name = project.name
    val coverUri  = project.coverUri
    val createdAt = project.createdAt
    val updatedAt = project.updatedAt
}