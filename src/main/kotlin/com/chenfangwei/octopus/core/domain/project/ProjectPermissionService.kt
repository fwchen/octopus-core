package com.chenfangwei.octopus.core.domain.project

import com.chenfangwei.octopus.core.domain.project.model.Project
import org.springframework.stereotype.Service

@Service
class ProjectPermissionService {

    fun canOperateProject(project: Project, userId: String): Boolean {
        return project.creatorId == userId
    }
}