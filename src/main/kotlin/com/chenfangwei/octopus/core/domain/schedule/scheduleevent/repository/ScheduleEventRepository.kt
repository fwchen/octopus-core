package com.chenfangwei.octopus.core.domain.schedule.scheduleevent.repository

import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import com.chenfangwei.octopus.core.domain.schedule.scheduleevent.model.ScheduleEvent
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface ScheduleEventRepository: MongoRepository<ScheduleEvent, String> {
    @Query("{'userId': ?0, 'title': ?1, 'startTime': ?2, 'endTime': ?3}")
    fun findSameEvent(userId: String, title: String, startTime: Date, endTime: Date): ScheduleEvent?
    fun findByUserIdAndLinkId(userId: String, linkId: String): ScheduleEvent?
}