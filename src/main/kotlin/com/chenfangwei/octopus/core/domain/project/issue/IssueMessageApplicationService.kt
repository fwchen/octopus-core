package com.chenfangwei.octopus.core.domain.project.issue

import com.chenfangwei.octopus.core.domain.project.issue.event.UpdatedIssueEvent
import com.chenfangwei.octopus.core.share.IssueRabbitmqExchange
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class IssueMessageApplicationService(private val rabbitTemplate: RabbitTemplate) {

    fun sendUpdatedIssue(event: UpdatedIssueEvent) {
        rabbitTemplate.convertAndSend(IssueRabbitmqExchange, event);
    }
}