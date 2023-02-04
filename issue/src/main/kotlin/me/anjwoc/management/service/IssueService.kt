package me.anjwoc.management.service

import me.anjwoc.management.domain.Issue
import me.anjwoc.management.model.IssueRequest
import me.anjwoc.management.model.IssueResponse
import me.anjwoc.management.repository.IssueRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IssueService (
    private val issueRepository: IssueRepository,
) {
   @Transactional
   fun create(userId: Long, request: IssueRequest): IssueResponse {
        val issue = Issue(
            summary = request.summary,
            description = request.description,
            userId = userId,
            type = request.type,
            priority = request.priority,
            status = request.status
        )

       return IssueResponse(issueRepository.save(issue))
   }
}