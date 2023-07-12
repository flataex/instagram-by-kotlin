package com.flata.instagram.domain.follow.controller

import com.flata.instagram.domain.follow.controller.dto.FollowRegistrationRequest
import com.flata.instagram.domain.follow.controller.dto.FollowResponse
import com.flata.instagram.domain.follow.facade.FollowFacade
import com.flata.instagram.global.model.Authorization
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/follow")
class FollowController(
    private val followFacade: FollowFacade
) {

    @PostMapping("/{toUserId}")
    fun save(
        @Authorization fromUserId: Long,
        @PathVariable(value = "toUserId") toUserId: Long
    ): ResponseEntity<Void> {
        followFacade.save(
            FollowRegistrationRequest(
                fromUserId = fromUserId,
                toUserId = toUserId
            )
        )
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping
    fun findAll(
        @Authorization userId: Long
    ) : ResponseEntity<FollowResponse> = ResponseEntity.ok(
        followFacade.findAll(userId)
    )

    @DeleteMapping("/{toUserId}")
    fun cancelFollow(
        @Authorization fromUserId: Long,
        @PathVariable(value = "toUserId") toUserId: Long
    ): ResponseEntity<Void> {
        followFacade.cancelFollow(fromUserId, toUserId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}