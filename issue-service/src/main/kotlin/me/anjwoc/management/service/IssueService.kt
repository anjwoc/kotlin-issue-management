package me.anjwoc.management.service

import me.anjwoc.management.domain.Issue
import me.anjwoc.management.domain.enums.IssueStatus
import me.anjwoc.management.exception.NotFoundException
import me.anjwoc.management.model.IssueRequest
import me.anjwoc.management.model.IssueResponse
import me.anjwoc.management.repository.IssueRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
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

    fun findAll(status: IssueStatus): List<IssueResponse>? =
        issueRepository.findAllByStatusOrderByCreatedAtDesc(status)
            ?.map { IssueResponse(it) }

    fun findById(id: Long): IssueResponse {
        val issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("이슈가 존재하지 않습니다.")
        return IssueResponse(issue)
    }

    @Transactional
    fun updateIssue(userId: Long, id: Long, request: IssueRequest): IssueResponse {
        val issue: Issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("이슈가 존재하지 않습니다")

        return with(issue) {
            summary = request.summary
            description = request.description
            this.userId = userId
            type = request.type
            priority = request.priority
            status = request.status

            IssueResponse(issueRepository.save(this))
        }
    }

    fun deleteIssue(id: Long) {
        issueRepository.deleteById(id)
    }


}