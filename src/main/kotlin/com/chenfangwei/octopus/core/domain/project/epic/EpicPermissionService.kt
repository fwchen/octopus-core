package com.chenfangwei.octopus.core.domain.project.epic

import com.chenfangwei.octopus.core.domain.project.ProjectPermissionService
import com.chenfangwei.octopus.core.domain.project.epic.model.Epic
import org.springframework.stereotype.Service

@Service
class EpicPermissionService(
        private val projectPermissionService: ProjectPermissionService
) {

    fun canOperateEpic(epic: Epic, userId: String): Boolean {
        return projectPermissionService.canOperateProject(epic.getProjectId(), userId)
    }
}