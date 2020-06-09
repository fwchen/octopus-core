package com.chenfangwei.octopus.core.domain.project

import com.chenfangwei.octopus.core.domain.project.model.Project
import com.chenfangwei.octopus.core.domain.project.repository.ProjectRepository
import com.chenfangwei.octopus.core.share.exception.EntityNotFoundException
import com.chenfangwei.octopus.core.share.exception.ResourcePermissionException
import org.springframework.stereotype.Service

@Service
class ProjectPermissionService(private val projectRepository: ProjectRepository) {

    fun canOperateProject(project: Project, userId: String): Boolean {
        return project.creatorId == userId
    }

    fun canOperateProject(projectId: String, userId: String): Boolean {
        val project = projectRepository.findById(projectId).orElseThrow { EntityNotFoundException() }
        return canOperateProject(project, userId)
    }

    fun guardOperationProject(projectId: String, userId: String) {
        if (!this.canOperateProject(projectId, userId)) {
            throw ResourcePermissionException()
        }
    }
}