package com.chenfangwei.octopus.core.domain.project.command

data class SetProjectCoverCommand(val projectId: String, val coverCode: String) {
    lateinit var userId: String
}