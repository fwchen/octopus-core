package com.chenfangwei.octopus.core.domain.project.issue.command

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class CreateCommentCommand(@field:NotEmpty @field:NotBlank val content: String, val issueId: String) {
    lateinit var userId: String
}