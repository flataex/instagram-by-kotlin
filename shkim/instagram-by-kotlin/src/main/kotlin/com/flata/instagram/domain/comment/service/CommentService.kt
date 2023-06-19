package com.flata.instagram.domain.comment.service

import com.flata.instagram.domain.comment.controller.dto.CommentSaveRequest
import com.flata.instagram.domain.comment.model.Comment
import com.flata.instagram.domain.comment.repository.CommentRepository
import com.flata.instagram.domain.feed.repository.FeedRepository
import com.flata.instagram.domain.user.repository.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommentService(
    private val userRepository: UserRepository,
    private val feedRepository: FeedRepository,
    private val commentRepository: CommentRepository
) {

    fun save(
        userId: Long,
        request: CommentSaveRequest
    ): Long {
        return Comment(
                user = userRepository.findByIdOrNull(userId) ?: throw NotFoundException(),
                feed = feedRepository.findByIdOrNull(request.feedId) ?: throw NotFoundException(),
                content = request.content
            ).let { commentRepository.save(it) }
            .id
    }

    fun delete(
        userId: Long,
        commentId: Long
    ) {
        commentRepository.findByIdOrNull(commentId)
            ?.takeIf { it.user.id == userId }
            ?.delete()
            ?: throw NotFoundException()
    }
}