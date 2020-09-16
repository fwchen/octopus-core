package com.chenfangwei.octopus.core.domain.schedule.scheduleevent.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("schedule_event")
class ScheduleEvent(
        @Id
        val id: String,
        var title: String,
        val type: ScheduleEventType,
        var startTime: Date,
        var endTime: Date,
        val userId: String,
        val linkId: String?
) {

}