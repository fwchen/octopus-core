package com.chenfangwei.octopus.core.domain.project.issue

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class IssueRabbitmqConfigure {

    @Bean
    fun queue(): Queue? {
        return Queue(IssueRabbitmqUpdatedEventQueue, false)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(IssueRabbitmqExchange)
    }

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#")
    }
}
