package me.anjwoc.management.model

import com.fasterxml.jackson.annotation.JsonFormat
import me.anjwoc.management.domain.Comment
import me.anjwoc.management.domain.Issue
import me.anjwoc.management.domain.enums.IssuePriority
import me.anjwoc.management.domain.enums.IssueStatus
import me.anjwoc.management.domain.enums.IssueType
import java.time.LocalDateTime

data class IssueRequest (
    val summary: String,
    val description: String,
    val type: IssueType,
    val priority: IssuePriority,
    val status: IssueStatus
)

data class IssueResponse (
    val id: Long,
    val comments: List<CommentResponse> = emptyList(),
    val summary: String,
    val description: String,
    val userId: Long,
    val type: IssueType,
    val priority: IssuePriority,
    val status: IssueStatus,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime?,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime?,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val deletedAt: LocalDateTime?,
) {
    companion object {
        operator fun invoke(issue: Issue) =
            with(issue) {
                IssueResponse(
                    id = id!!,
                    summary = summary,
                    description = description,
                    userId = userId,
                    comments = comments.sortedByDescending(Comment::id).map(Comment::toResponse),
                    type = type,
                    priority = priority,
                    status = status,
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                    deletedAt = deletedAt
                )
            }
    }
}