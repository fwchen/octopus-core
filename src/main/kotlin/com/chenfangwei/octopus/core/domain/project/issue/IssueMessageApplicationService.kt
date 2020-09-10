package com.chenfangwei.octopus.core.domain.project.issue

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service


@Service
class IssueMessageApplicationService(private val rabbitTemplate: RabbitTemplate) {


    init {
        sendUpdatedIssue()
    }

    fun sendUpdatedIssue() {
        rabbitTemplate.convertAndSend("hello", "foo.bar.baz", "Hello from RabbitMQ!");
    }

}