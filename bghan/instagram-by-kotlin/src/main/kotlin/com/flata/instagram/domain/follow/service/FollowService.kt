package com.flata.instagram.domain.follow.service

import com.flata.instagram.domain.follow.model.Follow
import com.flata.instagram.domain.follow.repository.FollowRepository
import com.flata.instagram.domain.user.model.User
import com.flata.instagram.global.exception.follow.FollowNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FollowService(
    private val followRepository: FollowRepository
) {
    fun save(
        fromUser: User,
        toUser: User
    ) {
        followRepository.save(
            Follow(
                fromUser = fromUser,
                toUser = toUser
            )
        )
    }

    @Transactional(readOnly = true)
    fun existsFollowPair(
        fromUser: User,
        toUser: User
    ): Boolean = followRepository.existsByFromUserAndToUser(fromUser, toUser)

    @Transactional(readOnly = true)
    fun findByUserId(
        userId: Long
    ) : List<Follow> = followRepository.findByFromUserId(userId)

    fun delete(
        fromUserId: Long,
        toUserId: Long
    ) {
        followRepository.findByFromUserIdAndToUserId(fromUserId, toUserId)
            ?.delete()
            ?: throw FollowNotFoundException()
    }
}