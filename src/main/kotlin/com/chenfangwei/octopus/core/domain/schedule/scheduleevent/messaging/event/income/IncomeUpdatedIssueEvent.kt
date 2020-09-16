package com.chenfangwei.octopus.core.domain.schedule.scheduleevent.messaging.event.income

data class IncomeUpdatedIssueEvent(
        val id: String,
        val kanbanId: String,
        val columnId: String,
        val projectId: String,
        var creatorId: String,
        val title: String,
        var assigneeId: String
) {
}