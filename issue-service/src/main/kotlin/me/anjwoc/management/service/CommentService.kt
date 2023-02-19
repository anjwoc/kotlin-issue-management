package me.anjwoc.management.service

import me.anjwoc.management.domain.Comment
import me.anjwoc.management.exception.NotFoundException
import me.anjwoc.management.model.CommentRequest
import me.anjwoc.management.model.CommentResponse
import me.anjwoc.management.model.toResponse
import me.anjwoc.management.repository.CommentRepository
import me.anjwoc.management.repository.IssueRepository
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true)
class CommentService (
    private val commentRepository: CommentRepository,
    private val issuerRepository: IssueRepository
){
    @Transactional
    fun create(issueId: Long, userId: Long, username: String, request: CommentRequest): CommentResponse {
        val issue = issuerRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다.")

        val comment = Comment(
            issue = issue,
            userId = userId,
            username = username,
            body = request.body,
        )

        issue.comments.add(comment)

        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    fun edit(id: Long, userId: Long, request: CommentRequest): CommentResponse? {
        return commentRepository.findByIdAndUserId(id, userId)?.run {
            body = request.body
            commentRepository.save(this).toResponse()
        }
    }

    @Transactional
    fun delete(issueId: Long, commentId: Long, userId: Long) {
        val issue = issuerRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다.")
        commentRepository.findByIdAndUserId(commentId, userId)?.let { comment ->
            issue.comments.remove(comment)
        }
    }
}