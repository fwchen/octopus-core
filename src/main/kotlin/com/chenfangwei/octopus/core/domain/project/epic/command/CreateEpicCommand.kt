package com.chenfangwei.octopus.core.domain.project.epic.command

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class CreateEpicCommand(
        @field:NotEmpty @field:NotBlank val projectId: String,
        @field:NotEmpty @field:NotBlank val name: String
) {
    lateinit var userId: String
}
