package com.flata.instagram.domain.follow.facade

import com.flata.instagram.domain.follow.controller.dto.FollowRegistrationRequest
import com.flata.instagram.domain.follow.controller.dto.FollowResponse
import com.flata.instagram.domain.follow.converter.toResponse
import com.flata.instagram.domain.follow.service.FollowService
import com.flata.instagram.domain.user.model.User
import com.flata.instagram.domain.user.service.UserService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import javax.validation.Valid

@Component
@Transactional
@Validated
class FollowFacade(
    private val userService: UserService,
    private val followService: FollowService
) {
    fun save(
        @Valid request: FollowRegistrationRequest
    ) {
        val fromUser: User = userService.findBy(request.fromUserId)
        val toUser: User = userService.findBy(request.toUserId)

        followService.save(fromUser, toUser)
    }

    @Transactional(readOnly = true)
    fun findAll(
        userId: Long
    ) : FollowResponse? =
        followService.findByUserId(userId)
            .toResponse()

    fun cancelFollow(
        fromUserId: Long,
        toUserId: Long
    ) {
        followService.delete(fromUserId, toUserId)
    }
}