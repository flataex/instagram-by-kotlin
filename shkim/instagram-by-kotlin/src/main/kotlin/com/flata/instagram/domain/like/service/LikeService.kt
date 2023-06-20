package com.flata.instagram.domain.like.service

import com.flata.instagram.domain.feed.repository.FeedRepository
import com.flata.instagram.domain.like.controller.dto.LikeSaveRequest
import com.flata.instagram.domain.like.model.Like
import com.flata.instagram.domain.like.repository.LikeRepository
import com.flata.instagram.domain.user.repository.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LikeService(
    private val userRepository: UserRepository,
    private val feedRepository: FeedRepository,
    private val likeRepository: LikeRepository
) {

    fun save(
        userId: Long,
        request: LikeSaveRequest
    ): Long {
        return Like(
                user = userRepository.findByIdOrNull(userId) ?: throw NotFoundException(),
                feed = feedRepository.findByIdOrNull(request.feedId) ?: throw NotFoundException()
            ).let { likeRepository.save(it) }
            .id
    }

    fun delete(
        userId: Long,
        likeId: Long
    ) {
        likeRepository.findByIdOrNull(likeId)
            ?.takeIf { it.user.id == userId }
            ?.delete()
            ?: throw NotFoundException()
    }
}