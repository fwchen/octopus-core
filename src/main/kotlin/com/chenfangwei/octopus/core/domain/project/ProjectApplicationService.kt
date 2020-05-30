package com.chenfangwei.octopus.core.domain.project

import com.chenfangwei.octopus.core.domain.project.command.CreateProjectCommand
import com.chenfangwei.octopus.core.domain.project.command.SetProjectCoverCommand
import com.chenfangwei.octopus.core.domain.project.factory.ProjectFactory
import com.chenfangwei.octopus.core.domain.project.model.Project
import com.chenfangwei.octopus.core.domain.project.repository.ProjectRepository
import com.chenfangwei.octopus.core.share.exception.EntityNotFoundException
import com.chenfangwei.octopus.core.share.exception.ResourcePermissionException
import com.chenfangwei.octopus.core.storage.StorageService
import org.springframework.stereotype.Service
import javax.validation.Valid

@Service
class ProjectApplicationService(private val projectRepository: ProjectRepository,
                                private val projectFactory: ProjectFactory,
                                private val projectPermissionService: ProjectPermissionService,
                                private val storageService: StorageService) {


    fun queryProjectList(creatorId: String): List<Project> {
        return projectRepository.findAllByCreatorId(creatorId)
    }

    fun createProject(command: CreateProjectCommand) {
        val project = projectFactory.project(command.identify, command.name, command.creatorId)
        projectRepository.save(project)
    }

    fun setProjectCover(command: @Valid SetProjectCoverCommand) {
        val project = projectRepository.findById(command.projectId).orElseThrow{ EntityNotFoundException("project not found") }
        if (!projectPermissionService.canOperateProject(project, command.userId)) {
            throw ResourcePermissionException()
        }
        project.coverUri = storageService.saveBase64PngImage(command.coverCode)
        projectRepository.save(project)
    }
}