package com.flata.instagram.domain.reply.service

import com.flata.instagram.domain.comment.repository.CommentRepository
import com.flata.instagram.domain.reply.controller.dto.ReplySaveRequest
import com.flata.instagram.domain.reply.model.Reply
import com.flata.instagram.domain.reply.repository.ReplyRepository
import com.flata.instagram.domain.user.repository.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ReplyService(
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository,
    private val replyRepository: ReplyRepository
) {

    fun save(
        userId: Long,
        request: ReplySaveRequest
    ): Long {
        return Reply(
                user = userRepository.findByIdOrNull(userId) ?: throw NotFoundException(),
                comment = commentRepository.findByIdOrNull(request.commentId) ?: throw NotFoundException(),
                content = request.content
            ).let { replyRepository.save(it) }
            .id
    }

    fun delete(
        userId: Long,
        replyId: Long
    ) {
        replyRepository.findByIdOrNull(replyId)
            ?.takeIf { it.user.id == userId }
            ?.delete()
            ?: throw NotFoundException()
    }
}