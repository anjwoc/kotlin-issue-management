package me.anjwoc.management.controller

import me.anjwoc.management.config.AuthUser
import me.anjwoc.management.model.IssueRequest
import me.anjwoc.management.service.IssueService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issue")
class IssueController(
    private val issueService: IssueService,
) {
    @PostMapping
    fun create(
        authUser: AuthUser,
        @RequestBody request: IssueRequest,
    ) = issueService.create(authUser.userId, request)
}