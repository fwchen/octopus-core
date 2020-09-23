package com.chenfangwei.octopus.core.domain.schedule.event.messaging

import com.chenfangwei.octopus.core.share.serializer.DateSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UpdatedIssueMessaging(
        val id: String,
        val kanbanId: String? = null,
        val columnId: String? = null,
        val projectId: String,
        val creatorId: String,
        @Serializable(with = DateSerializer::class)
        val startTime: Date? = null,
        @Serializable(with = DateSerializer::class)
        val deadline: Date? = null,
        val title: String,
        val assigneeId: String? = null,
        var order: Float ? = null
) {
}