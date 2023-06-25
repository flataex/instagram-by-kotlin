package com.flata.instagram.domain.follow.controller

import com.flata.instagram.domain.follow.dto.FollowRequest
import com.flata.instagram.domain.follow.service.FollowService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession
import javax.validation.Valid

@RestController
@RequestMapping("/follows")
class FollowController(
    private val followService: FollowService
) {
    @PostMapping
    fun follow(
        @Valid @RequestBody followRequest: FollowRequest,
        httpSession: HttpSession
    ): ResponseEntity<Unit> =
        followService.follow(followRequest, httpSession.getAttribute("userId") as Long)

    @DeleteMapping
    fun unfollow(
        @Valid @RequestBody followRequest: FollowRequest,
        httpSession: HttpSession
    ): ResponseEntity<Unit> =
        followService.unfollow(followRequest, httpSession.getAttribute("userId") as Long)
}