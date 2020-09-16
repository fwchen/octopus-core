package com.chenfangwei.octopus.core.domain.project.issue

import com.chenfangwei.octopus.core.domain.project.issue.command.CreateCommentCommand
import com.chenfangwei.octopus.core.domain.project.issue.command.CreateIssueCommand
import com.chenfangwei.octopus.core.domain.project.issue.command.UpdateIssueCommand
import com.chenfangwei.octopus.core.domain.project.issue.messaging.UpdatedIssueEvent
import com.chenfangwei.octopus.core.domain.project.issue.factory.IssueFactory
import com.chenfangwei.octopus.core.domain.project.issue.model.Issue
import com.chenfangwei.octopus.core.domain.project.issue.repository.IssueRepository
import com.chenfangwei.octopus.core.share.exception.EntityNotFoundException
import com.chenfangwei.octopus.core.storage.StorageService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayOutputStream

@Service
class IssueApplicationService(
        private val issueRepository: IssueRepository,
        private val issueFactory: IssueFactory,
        private val issueMessageApplicationService: IssueMessageApplicationService,
        private val storageService: StorageService) {

    fun createIssue(command: CreateIssueCommand): String {
        val maxOrderIssue = this.issueRepository.findTop1ByProjectIdOrderByOrderDesc(command.projectId)
        val issue = issueFactory.issue(command)
        issue.initOrderByMaxIssue(maxOrderIssue)
        issueRepository.save(issue)
        return issue.id
    }

    fun queryColumnIssues(columnId: String): List<Issue> {
        return issueRepository.findAllByColumnId(columnId)
    }

    fun queryIssueDetail(issueId: String): Issue {
        return issueRepository.findById(issueId).orElseThrow { EntityNotFoundException() }
    }

    fun updateIssue(command: UpdateIssueCommand) {
        val issue = issueRepository.findById(command.id).orElseThrow { EntityNotFoundException() }
        command.copyToIssue(issue)
        issueRepository.save(issue)
        issueMessageApplicationService.sendUpdatedIssue(UpdatedIssueEvent(issue))
    }

    fun queryProjectIssues(projectId: String): List<Issue> {
        return issueRepository.findAllByProjectId(projectId)
    }

    fun kanbanRecentlyIssues(kanbanId: String): List<Issue> {
        return issueRepository.findTop10ByKanbanIdOrderByUpdatedAtDesc(kanbanId)
    }

    fun removeIssue(issueId: String) {
        val issue = issueRepository.findById(issueId).orElseThrow { EntityNotFoundException() }
        issue.remove()
        issueRepository.save(issue)
    }

    fun createComment(command: CreateCommentCommand) {
        val issue = issueRepository.findById(command.issueId).orElseThrow { EntityNotFoundException() }
        issue.addComment(command.userId, command.content)
        issueRepository.save(issue)
    }

    // TODO lock
    @Transactional
    fun saveAttachment(issueId: String, userId: String, file: MultipartFile) {
        val issue = issueRepository.findById(issueId).orElseThrow { EntityNotFoundException() }
        val objectId = storageService.saveFile(file)
        issue.addAttachment(objectId, file.originalFilename, file.contentType, userId)
        issueRepository.save(issue)
    }

    fun getAttachment(issueId: String, attachmentId: String): ByteArrayOutputStream {
        val issue = issueRepository.findById(issueId).orElseThrow { EntityNotFoundException() }
        val attachment = issue.findAttachment(attachmentId)
        val outputStream = ByteArrayOutputStream()
        storageService.receiveFile(attachment.objectId)!!.transferTo(outputStream)
        return outputStream
    }

    fun deleteComment(issueId: String, commentId: String) {
        val issue = issueRepository.findById(issueId).orElseThrow { EntityNotFoundException() }
        issue.removeComment(commentId)
        issueRepository.save(issue)
    }

    @Transactional
    fun deleteAttachment(issueId: String, userId: String, attachmentId: String) {
        val issue = issueRepository.findById(issueId).orElseThrow { EntityNotFoundException() }
        val attachment = issue.findAttachment(attachmentId)
        issue.removeAttachment(attachmentId)
        storageService.removeFile(attachment.objectId)
        issueRepository.save(issue)
    }
}