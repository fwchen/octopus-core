package com.chenfangwei.octopus.core.domain.project.issue.command

import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import java.util.*

data class UpdateIssueCommand(val id: String) {
    var title: String? = null
    var desc: String? = null
    var assigneeId: String? = null
    var columnId: String? = null
    var deadline: Date? = null
    var deadlineDone: Boolean? = null
    lateinit var userId: String

    fun copyToIssue(issue: Issue) {
        if (this.title != null) {
            issue.title = this.title!!
        }
        if (this.desc != null) {
            issue.desc = this.desc!!
        }
        if (this.assigneeId != null) {
            issue.assigneeId = this.assigneeId!!
        }
        if (this.columnId != null) {
            issue.columnId = this.columnId!!
        }
        if (this.deadline != null) {
            issue.deadline = this.deadline!!
        }
        if (this.deadlineDone != null) {
            issue.deadlineDone = this.deadlineDone!!
        }
    }
}