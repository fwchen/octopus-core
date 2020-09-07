package com.chenfangwei.octopus.core.domain.project.epic

import com.chenfangwei.octopus.core.domain.project.epic.command.CreateEpicCommand
import com.chenfangwei.octopus.core.domain.project.epic.model.Epic
import com.chenfangwei.octopus.core.domain.project.epic.repository.EpicRepository
import org.springframework.stereotype.Service

@Service
class EpicApplicationService(
        private val epicRepository: EpicRepository
) {

    fun createEpic(command: CreateEpicCommand) {
        val epic = Epic.create(command.projectId, command.name)
        epicRepository.save(epic)
    }

    fun getProjectEpics(projectId: String): List<Epic> {
        return epicRepository.findAllByProjectId(projectId)
    }
}