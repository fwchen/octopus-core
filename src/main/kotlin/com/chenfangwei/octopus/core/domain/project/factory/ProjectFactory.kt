package com.chenfangwei.octopus.core.domain.project.factory

import com.chenfangwei.octopus.core.domain.project.model.Project
import com.chenfangwei.octopus.core.domain.project.model.ProjectSetting
import org.springframework.stereotype.Service

@Service
class ProjectFactory {
    fun project(id: String, name: String, creatorId: String): Project {
        val project = Project(id, name, creatorId)
        project.projectSetting = ProjectSetting()
        return project
    }
}