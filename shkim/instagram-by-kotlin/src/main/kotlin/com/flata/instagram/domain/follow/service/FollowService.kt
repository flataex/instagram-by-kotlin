package com.flata.instagram.domain.follow.service

import com.flata.instagram.domain.follow.model.Follow
import com.flata.instagram.domain.follow.repository.FollowRepository
import com.flata.instagram.domain.user.repository.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FollowService(
    private val userRepository: UserRepository,
    private val followRepository: FollowRepository
) {

    fun save(
        userId: Long,
        toUserId: Long
    ): Long {
        return Follow(
                fromUser = userRepository.findByIdOrNull(userId) ?: throw NotFoundException(),
                toUser = userRepository.findByIdOrNull(toUserId) ?: throw NotFoundException(),
            ).let { followRepository.save(it) }
            .id
    }

    fun delete(
        userId: Long,
        followId: Long
    ) {
        followRepository.findByIdOrNull(followId)
            ?.takeIf { it.fromUser.id == userId }
            ?.delete()
            ?: throw NotFoundException()
    }
}