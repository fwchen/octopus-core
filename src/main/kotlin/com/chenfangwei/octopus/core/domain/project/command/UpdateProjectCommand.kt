package com.chenfangwei.octopus.core.domain.project.command

import com.chenfangwei.octopus.core.domain.project.model.Project

data class UpdateProjectCommand(val id: String) {
    var name: String?  = null

    fun copyToProject(project: Project) {
        project.name = this.name ?: project.name
    }
}