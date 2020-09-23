package com.chenfangwei.octopus.core.domain.schedule.scheduleevent

import com.chenfangwei.octopus.core.domain.schedule.scheduleevent.factory.ScheduleEventFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class ScheduleEventApplicationService(
        private val scheduleEventFactory: ScheduleEventFactory,
        private val scheduleEventService: ScheduleEventService) {

    fun handleIssueScheduleEvent(title: String, startTime: Date, endTime: Date, userId: String, linkId: String) {
        val scheduleEvent = scheduleEventFactory.createIssueScheduleEvent(
                title = title, startTime = startTime, endTime = endTime, userId = userId, linkId = linkId
        )
        scheduleEventService.save(scheduleEvent)
    }
}