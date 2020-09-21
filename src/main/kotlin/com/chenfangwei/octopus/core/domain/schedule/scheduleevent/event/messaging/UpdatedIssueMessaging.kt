package com.chenfangwei.octopus.core.domain.schedule.scheduleevent.event.messaging

@Serializable
data class UpdatedIssueMessaging(
        val id: String,
        val kanbanId: String,
        val columnId: String,
        val projectId: String,
        var creatorId: String,
        val title: String,
        var assigneeId: String
) {
}