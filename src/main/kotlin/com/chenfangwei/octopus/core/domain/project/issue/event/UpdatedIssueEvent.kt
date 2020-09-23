package com.chenfangwei.octopus.core.domain.project.issue.event

import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import com.chenfangwei.octopus.core.share.serializer.DateSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class UpdatedIssueEvent(@Transient private val issue: Issue? = null): java.io.Serializable  {
    val id = issue!!.id
    val kanbanId = issue!!.kanbanId
    val columnId = issue!!.columnId
    val projectId = issue!!.projectId
    val creatorId = issue!!.creatorId
    @Serializable(with = DateSerializer::class)  val startTime = issue!!.startTime
    @Serializable(with = DateSerializer::class) val deadline = issue!!.deadline
    val title = issue!!.title
    var assigneeId = issue!!.assigneeId
    var order = issue!!.order
}