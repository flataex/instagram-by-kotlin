package com.flata.instagram.domain.like.controller

import com.flata.instagram.domain.like.controller.dto.LikeRequest
import com.flata.instagram.domain.like.facade.LikeFacade
import com.flata.instagram.global.model.Authorization
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/likes")
class LikeController(
    private val likeFacade: LikeFacade
) {

    @PostMapping("/{feedId}")
    fun save(
        @Authorization userId: Long,
        @PathVariable(value = "feedId") feedId: Long
    ): ResponseEntity<Void> {
        likeFacade.save(
            LikeRequest(
                userId = userId,
                feedId = feedId
            )
        )
        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping("/{feedId}")
    fun dislike(
        @Authorization userId: Long,
        @PathVariable(value = "feedId") feedId: Long
    ): ResponseEntity<Void> {
        likeFacade.dislike(userId, feedId)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}