package com.chenfangwei.octopus.core.domain.schedule.event

import com.chenfangwei.octopus.core.domain.schedule.event.messaging.UpdatedIssueMessaging
import com.chenfangwei.octopus.core.share.IssueRabbitmqUpdatedEventQueue
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class ScheduleEventMessageApplicationService() {

    @RabbitListener(queues = [IssueRabbitmqUpdatedEventQueue])
    fun receiveMessageFromFanout1(message: Message) {
        val updatedIssueMessaging = Json.decodeFromString<UpdatedIssueMessaging>(String(message.body))
        println(updatedIssueMessaging)
    }
}