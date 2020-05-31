package com.chenfangwei.octopus.core.domain.project.command

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class CreateProjectCommand(
        @NotBlank(message = "identify is required") @Pattern(regexp = "^[A-Za-z][A-Za-z0-9]+$") val identify: String,
        @NotBlank(message = "name is required") val name: String) {
    lateinit var creatorId: String
}