package com.chenfangwei.octopus.core.domain.schedule.scheduleevent.factory

import com.chenfangwei.octopus.core.domain.schedule.scheduleevent.model.ScheduleEvent
import com.chenfangwei.octopus.core.domain.schedule.scheduleevent.model.ScheduleEventType
import com.chenfangwei.octopus.core.share.factory.generateId
import org.springframework.stereotype.Component
import java.util.*

@Component
class ScheduleEventFactory {

    fun createIssueScheduleEvent(title: String, startTime: Date, endTime: Date, userId: String, linkId: String): ScheduleEvent {
        val id = generateId()
        return  ScheduleEvent(
                id,
                title,
                ScheduleEventType.Issue,
                startTime,
                endTime,
                userId,
                linkId
        )
    }
}