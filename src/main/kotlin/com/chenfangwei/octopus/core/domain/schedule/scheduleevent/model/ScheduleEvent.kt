package com.chenfangwei.octopus.core.domain.schedule.scheduleevent.model

import java.util.*

class ScheduleEvent(
        val id: String,
        var title: String,
        val type: ScheduleEventType,
        var startTime: Date,
        var endTime: Date,
        val userId: String
) {
    
}