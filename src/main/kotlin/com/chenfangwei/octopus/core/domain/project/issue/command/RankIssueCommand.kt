package com.chenfangwei.octopus.core.domain.project.issue.command

data class RankIssueCommand(
        val issueId: String,
        val targetIssueId: String,
        val isBefore: Boolean)