package com.flata.instagram.domain.like.controller

import com.flata.instagram.domain.follow.model.dto.FollowResponse
import com.flata.instagram.domain.like.model.dto.LikeRequest
import com.flata.instagram.domain.like.model.dto.LikeResponse
import com.flata.instagram.domain.like.service.LikeService
import com.flata.instagram.global.utils.JwtProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession
import javax.validation.Valid

@RestController
@RequestMapping("/api/like")
class LikeController(
    private val likeService: LikeService,
    private val jwtProvider: JwtProvider
) {
    @PostMapping
    fun like(@Valid likeRequest: LikeRequest,
               session: HttpSession
    ): ResponseEntity<FollowResponse> {
        var userId = jwtProvider.getIdFromJwtToken(session.getAttribute("USER_TOKEN").toString())
        likeService.like(likeRequest,
            userId)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping
    fun disableLike(@Valid likeRequest: LikeRequest,
                 session: HttpSession
    ): ResponseEntity<FollowResponse> {
        var userId = jwtProvider.getIdFromJwtToken(session.getAttribute("USER_TOKEN").toString())
        likeService.disableLike(likeRequest,
            userId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
    @GetMapping("/{feedId}")
    fun getLikeUser(@PathVariable feedId: Long): ResponseEntity<List<LikeResponse>> {
        val likeUsers = likeService.getLikeUsers(feedId)

        return ResponseEntity.ok(likeUsers)
    }
}