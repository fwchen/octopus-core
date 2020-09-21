package com.chenfangwei.octopus.core.domain.schedule.scheduleevent.event

import com.chenfangwei.octopus.core.domain.schedule.scheduleevent.event.messaging.UpdatedIssueMessaging
import com.chenfangwei.octopus.core.share.IssueRabbitmqUpdatedEventQueue
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class ScheduleEventMessageApplicationService() {

    @RabbitListener(queues = [IssueRabbitmqUpdatedEventQueue])
    fun receiveMessageFromFanout1(message: UpdatedIssueMessaging) {
        println("Received fanout 1 message: $message")
    }
}