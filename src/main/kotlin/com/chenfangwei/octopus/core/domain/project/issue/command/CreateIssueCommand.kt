package com.chenfangwei.octopus.core.domain.project.issue.command

data class CreateIssueCommand( val title: String) {
    lateinit var userId: String
}