package com.chenfangwei.octopus.core.domain.project

import com.chenfangwei.octopus.core.domain.project.command.CreateProjectCommand
import com.chenfangwei.octopus.core.domain.project.factory.ProjectFactory
import com.chenfangwei.octopus.core.domain.project.model.Project
import com.chenfangwei.octopus.core.domain.project.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectApplicationService(private val projectRepository: ProjectRepository, private val projectFactory: ProjectFactory) {


    fun queryProjectList(creatorId: Long): List<Project> {
        return projectRepository.findAllByCreatorId(creatorId)
    }

    fun createProject(command: CreateProjectCommand) {
        val project = projectFactory.project(command.identify, command.name, command.creatorId)
        projectRepository.save(project)
    }
}