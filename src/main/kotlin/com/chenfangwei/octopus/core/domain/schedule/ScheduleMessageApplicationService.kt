package com.chenfangwei.octopus.core.domain.schedule

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service


@Service
class ScheduleMessageApplicationService {

    @RabbitListener(queues = ["hello-queue"])
    fun receiveMessageFromFanout1(message: String) {
        println("Received fanout 1 message: $message")
    }
}