package com.chenfangwei.octopus.core.domain.schedule.scheduleevent

import com.chenfangwei.octopus.core.domain.schedule.scheduleevent.model.ScheduleEvent
import com.chenfangwei.octopus.core.domain.schedule.scheduleevent.repository.ScheduleEventRepository
import org.springframework.stereotype.Service

@Service
class ScheduleEventService(private val scheduleEventRepository: ScheduleEventRepository) {



    fun saveEvent(event: ScheduleEvent) {
        scheduleEventRepository.save(event)
    }


}