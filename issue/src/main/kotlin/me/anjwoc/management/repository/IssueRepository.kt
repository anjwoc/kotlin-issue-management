package me.anjwoc.management.repository

import me.anjwoc.management.domain.Issue
import org.springframework.data.jpa.repository.JpaRepository

interface IssueRepository : JpaRepository<Issue, Long> {
}