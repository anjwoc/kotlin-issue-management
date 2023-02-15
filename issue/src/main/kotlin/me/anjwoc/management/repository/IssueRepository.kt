package me.anjwoc.management.repository

import me.anjwoc.management.domain.Issue
import me.anjwoc.management.domain.enums.IssueStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface IssueRepository : JpaRepository<Issue, Long> {
    fun findAllByStatusOrderByCreatedAtDesc(status: IssueStatus): List<Issue>?
}