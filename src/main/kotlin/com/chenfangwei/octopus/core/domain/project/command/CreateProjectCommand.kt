package com.chenfangwei.octopus.core.domain.project.command

data class CreateProjectCommand(val identify: String, val name: String) {
    lateinit var creatorId: String
}