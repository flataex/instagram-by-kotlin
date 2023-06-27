package com.flata.instagram.domain.comment.service

import com.flata.instagram.domain.comment.model.dto.CommentRequest
import com.flata.instagram.domain.comment.model.dto.CommentResponse
import com.flata.instagram.domain.comment.model.entity.Comment
import com.flata.instagram.domain.comment.repository.CommentRepository
import com.flata.instagram.domain.feed.repository.FeedRepository
import com.flata.instagram.domain.user.repository.UserRepository
import com.flata.instagram.global.utils.JwtProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val feedRepository: FeedRepository,
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
    private val commentRepository: CommentRepository
) {

    @Transactional
    fun createComment(jwt: String, commentRequest: CommentRequest) {
        val userId = jwtProvider.getIdFromJwtToken(jwt)
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("유저가 존재하지 않습니다") }
        val feed = feedRepository.findById(commentRequest.feedId).orElseThrow { IllegalArgumentException("피드가 존재하지 않습니다") }

        commentRepository.save(Comment(user = user, feed = feed, content = commentRequest.content))
    }

    @Transactional
    fun findByFeedId(feedId: Long): List<CommentResponse> {
        val feed = feedRepository.findById(feedId).orElseThrow { IllegalArgumentException("피드가 존재하지 않습니다") }
        val comments = commentRepository.findByFeed(feed)

        return comments.map { CommentResponse(it.user.email, it.user.nickname, it.content) }
    }

    @Transactional
    fun deleteComment(jwt: String, commentId: Long) {
        val userId = jwtProvider.getIdFromJwtToken(jwt)
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("유저가 존재하지 않습니다") }
        val comment = commentRepository.findByUserAndId(user, commentId) ?: throw IllegalArgumentException("댓글이 존재하지 않습니다")

        commentRepository.delete(comment)
    }

    @Transactional
    fun updateComment(jwt: String, commentId: Long, commentRequest: CommentRequest): CommentResponse {
        val userId = jwtProvider.getIdFromJwtToken(jwt)
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("유저가 존재하지 않습니다") }
        val comment = commentRepository.findByUserAndId(user, commentId) ?: throw IllegalArgumentException("댓글이 존재하지 않습니다")

        comment.content = commentRequest.content
        commentRepository.save(comment)

        return CommentResponse(user.email, user.nickname, comment.content)
    }
}