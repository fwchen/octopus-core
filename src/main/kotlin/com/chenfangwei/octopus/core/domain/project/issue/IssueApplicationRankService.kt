package com.chenfangwei.octopus.core.domain.project.issue

import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import com.chenfangwei.octopus.core.domain.project.issue.repository.IssueRepository
import com.chenfangwei.octopus.core.share.exception.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class IssueApplicationRankService(private val issueRepository: IssueRepository) {

    fun rankIssue(issueId: String, targetIssueId: String, isBefore: Boolean): List<Issue> {
        val changedIssues = ArrayList<Issue>()
        val targetIssue = issueRepository.findById(targetIssueId).orElseThrow{ EntityNotFoundException() }
        val issue = issueRepository.findById(issueId).orElseThrow{ EntityNotFoundException() }
        val nextIssue: Issue? = if (isBefore) issueRepository.findFirstByProjectIdAndOrderLessThanOrderByOrderDesc(issue.projectId, targetIssue.order) else issueRepository.findFirstByProjectIdAndOrderGreaterThanOrderByOrderAsc(issue.projectId, targetIssue.order)
        if (nextIssue == null) {
            issue.order = targetIssue.order - if (isBefore) 100F else -100F
            changedIssues.add(issue)
            return changedIssues
        }
        if (kotlin.math.abs(targetIssue.order - nextIssue.order) < 20F) {
//            var adjustIssue = if (isBefore) targetIssue else nextIssue
//            var baseIssue = if (isBefore) nextIssue else targetIssue
            changedIssues.addAll(adjustIssueOrder(nextIssue, targetIssue.order, 100F, isBefore))
        }
        issue.order = targetIssue.order + (if (isBefore) -1 else 1) * kotlin.math.abs(targetIssue.order - nextIssue.order) / 2
        changedIssues.add(issue)
        issueRepository.saveAll(changedIssues)
        return changedIssues
    }

    private fun adjustIssueOrder(issue: Issue, baseOrder: Float, adjustNumber: Float, isBefore: Boolean): List<Issue> {
        val realAdjustNumber = if (isBefore) -adjustNumber else adjustNumber
        val changedIssues = ArrayList<Issue>()
        val nextIssue = if (isBefore) issueRepository.findFirstByProjectIdAndOrderLessThanOrderByOrderDesc(issue.projectId, issue.order) else issueRepository.findFirstByProjectIdAndOrderGreaterThanOrderByOrderAsc(issue.projectId, issue.order)
        if (nextIssue == null) {
            issue.order += realAdjustNumber
            changedIssues.add(issue)
            return changedIssues
        }
        val diff = if (isBefore) issue.order - nextIssue.order else nextIssue.order - issue.order
        if (diff >= 2 * adjustNumber) {
            issue.order  = baseOrder +  realAdjustNumber
            changedIssues.add(issue)
           return changedIssues
        } else {
            changedIssues.addAll(adjustIssueOrder(nextIssue, baseOrder + realAdjustNumber, adjustNumber, isBefore))
            issue.order = baseOrder + realAdjustNumber
            changedIssues.add(issue)
        }
        return changedIssues
    }
}