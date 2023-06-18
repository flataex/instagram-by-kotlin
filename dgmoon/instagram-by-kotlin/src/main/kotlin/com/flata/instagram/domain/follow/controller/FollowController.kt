package com.flata.instagram.domain.follow.controller

import com.flata.instagram.domain.follow.dto.FollowRequest
import com.flata.instagram.domain.follow.dto.FollowResponse
import com.flata.instagram.domain.follow.service.FollowService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession
import javax.validation.Valid

@RestController
class FollowController(
    private val followService: FollowService
) {
    @PostMapping("/follow")
    fun follow(
        @Valid @RequestBody followRequest: FollowRequest,
        httpSession: HttpSession
    ): ResponseEntity<FollowResponse> =
        ResponseEntity.ok(
            FollowResponse(
                followService.follow(followRequest, httpSession.getAttribute("userId") as Long)
            )
        )

    @PostMapping("/unfollow")
    fun unfollow(
        @Valid @RequestBody followRequest: FollowRequest,
        httpSession: HttpSession
    ): ResponseEntity<FollowResponse> =
        ResponseEntity.ok(
            FollowResponse(
                followService.unfollow(followRequest, httpSession.getAttribute("userId") as Long)
            )
        )
}