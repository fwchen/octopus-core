package com.chenfangwei.octopus.core.domain.project.epic

import com.chenfangwei.octopus.core.constant.AuthUserIdKey
import com.chenfangwei.octopus.core.domain.project.ProjectPermissionService
import com.chenfangwei.octopus.core.domain.project.epic.command.CreateEpicCommand
import com.chenfangwei.octopus.core.domain.project.epic.model.Epic
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class EpicController(
        private val epicApplicationService: EpicApplicationService,
        private val epicPermissionService: EpicPermissionService,
        private val projectPermissionService: ProjectPermissionService
) {

    @GetMapping(value = ["/project/{projectId}/epics"])
    fun queryProjectEpics(@PathVariable projectId: String, @RequestHeader(AuthUserIdKey) userId: String): List<Epic> {
        projectPermissionService.canOperateProject(projectId, userId)
        return epicApplicationService.getProjectEpics(projectId)
    }

    @PostMapping(value = ["/project/{projectId}/epic"])
    fun createProjectEpic(@RequestBody command: @Valid CreateEpicCommand, @PathVariable projectId: String, @RequestHeader(AuthUserIdKey) userId: String) {
        projectPermissionService.canOperateProject(projectId, userId)
        epicApplicationService.createEpic(command)
    }
}