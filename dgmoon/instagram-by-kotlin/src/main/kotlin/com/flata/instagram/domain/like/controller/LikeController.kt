package com.flata.instagram.domain.like.controller

import com.flata.instagram.domain.like.dto.LikeRequest
import com.flata.instagram.domain.like.dto.LikeResponse
import com.flata.instagram.domain.like.service.LikeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession
import javax.validation.Valid

@RestController
@RequestMapping("/likes")
class LikeController(
    private val likeService: LikeService
) {
    @PostMapping
    fun like(@Valid @RequestBody likeRequest: LikeRequest, httpSession: HttpSession): ResponseEntity<LikeResponse> =
        likeService.like(likeRequest, httpSession.getAttribute("userId") as Long)

    @DeleteMapping
    fun unlike(@Valid @RequestBody likeRequest: LikeRequest, httpSession: HttpSession): ResponseEntity<LikeResponse> =
        likeService.unlike(likeRequest, httpSession.getAttribute("userId") as Long)
}