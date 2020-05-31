package com.chenfangwei.octopus.core.domain.project.command

data class SetProjectCoverCommand(val coverCode: String) {
    lateinit var userId: String
}