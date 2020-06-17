package com.chenfangwei.octopus.core.domain.project.issue.presenter

import com.chenfangwei.octopus.core.domain.project.issue.model.Issue

data class IssueChangedOrderDTO constructor(private val issue: Issue) {
    val id = issue.id
    val order = issue.order
}