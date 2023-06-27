package com.flata.instagram.domain.reply.service

import com.flata.instagram.domain.comment.repository.CommentRepository
import com.flata.instagram.domain.reply.model.dto.ReplyRequest
import com.flata.instagram.domain.reply.model.dto.ReplyResponse
import com.flata.instagram.domain.reply.model.entity.Reply
import com.flata.instagram.domain.reply.repository.ReplyRepository
import com.flata.instagram.domain.user.repository.UserRepository
import com.flata.instagram.global.utils.JwtProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReplyService(
    private val replyRepository: ReplyRepository,
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository,
    private val jwtProvider: JwtProvider
) {
    @Transactional
    fun createReply(jwt: String, replyRequest: ReplyRequest) {
        val userId = jwtProvider.getIdFromJwtToken(jwt)
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다") }
        val comment = commentRepository.findById(replyRequest.commentId).orElseThrow { IllegalArgumentException("댓글을 찾을 수 없습니다") }

        replyRepository.save(Reply(user = user, comment = comment, content = replyRequest.content))
    }

    @Transactional
    fun findByCommentId(commentId: Long): List<ReplyResponse> {
        val comment = commentRepository.findById(commentId).orElseThrow { IllegalArgumentException("댓글을 찾을 수 없습니다") }
        val replies = replyRepository.findByComment(comment)

        return replies.map { ReplyResponse(it.user.email, it.user.nickname, it.content) }
    }

    @Transactional
    fun deleteReply(jwt: String, replyId: Long) {
        val userId = jwtProvider.getIdFromJwtToken(jwt)
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다") }
        val reply = replyRepository.findByUserAndId(user, replyId) ?: throw IllegalArgumentException("답글을 찾을 수 없습니다")

        replyRepository.delete(reply)
    }

    @Transactional
    fun updateReply(jwt: String, replyId: Long, replyRequest: ReplyRequest): ReplyResponse {
        val userId = jwtProvider.getIdFromJwtToken(jwt)
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다") }
        val reply = replyRepository.findByUserAndId(user, replyId) ?: throw IllegalArgumentException("답글을 찾을 수 없습니다")

        reply.content = replyRequest.content
        replyRepository.save(reply)

        return ReplyResponse(user.email, user.nickname, reply.content)
    }
}
