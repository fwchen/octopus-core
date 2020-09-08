package com.chenfangwei.octopus.core.domain.project.issue.command

import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class UpdateIssueCommand(@field:NotEmpty @field:NotBlank val id: String, @field:NotEmpty @field:NotBlank var title: String) {
    var desc: String? = null
    var assigneeId: String? = null
    var columnId: String? = null
    var deadline: Date? = null
    var startTime: Date? = null
    var deadlineDone: Boolean? = null
    lateinit var userId: String

    fun copyToIssue(issue: Issue) {
        issue.title = this.title
        issue.desc = this.desc!!
        issue.assigneeId = this.assigneeId
        issue.columnId = this.columnId
        issue.deadline = this.deadline
        issue.startTime = this.startTime
        issue.deadlineDone = this.deadlineDone
    }


}