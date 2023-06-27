package com.flata.instagram.domain.follow.controller

import com.flata.instagram.domain.follow.model.dto.FollowRequest
import com.flata.instagram.domain.follow.model.dto.FollowResponse
import com.flata.instagram.domain.follow.model.dto.FollowerResponse
import com.flata.instagram.domain.follow.service.FollowService
import com.flata.instagram.global.utils.JwtProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession
import javax.validation.Valid

@RestController
@RequestMapping("/api/follow")
class FollowController(private val followService: FollowService,
                       private val jwtProvider: JwtProvider
) {


    @PostMapping
    fun follow( @Valid followRequest: FollowRequest,
                   session: HttpSession): ResponseEntity<FollowResponse> {
        var userId = jwtProvider.getIdFromJwtToken(session.getAttribute("USER_TOKEN").toString())
        followService.follow(followRequest,
            userId)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping
    fun unFollow( @Valid followRequest: FollowRequest,
                session: HttpSession): ResponseEntity<FollowResponse> {
        var userId = jwtProvider.getIdFromJwtToken(session.getAttribute("USER_TOKEN").toString())
        followService.unFollow(followRequest,
            userId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
    @GetMapping("/followers/{userId}")
    fun getFollowers(@PathVariable userId: Long): ResponseEntity<List<FollowerResponse>> {
        val followers = followService.getFollowers(userId)

        return ResponseEntity.ok(followers)
    }
}
