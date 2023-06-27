package com.flata.instagram.domain.follow.controller

import com.flata.instagram.domain.feed.service.FeedService
import com.flata.instagram.domain.feed.model.dto.FeedRequest
import com.flata.instagram.domain.feed.model.dto.FeedResponse
import com.flata.instagram.domain.feed.model.entity.Feed
import com.flata.instagram.domain.follow.model.dto.FollowRequest
import com.flata.instagram.domain.follow.model.dto.FollowResponse
import com.flata.instagram.domain.follow.model.dto.FollowerResponse
import com.flata.instagram.domain.follow.service.FollowService
import com.flata.instagram.domain.user.model.entity.User
import com.flata.instagram.global.utils.JwtProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
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
