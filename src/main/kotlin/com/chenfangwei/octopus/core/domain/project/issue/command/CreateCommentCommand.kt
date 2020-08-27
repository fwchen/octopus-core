package com.chenfangwei.octopus.core.domain.project.issue.command

import javax.validation.constraints.NotEmpty

data class CreateCommentCommand(@NotEmpty val content: String, val issueId: String) {
    lateinit var userId: String
}