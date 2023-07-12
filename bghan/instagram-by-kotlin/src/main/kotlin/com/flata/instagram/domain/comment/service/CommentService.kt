package com.flata.instagram.domain.comment.service

import com.flata.instagram.domain.comment.controller.dto.CommentDTO
import com.flata.instagram.domain.comment.controller.dto.ReplyDTO
import com.flata.instagram.domain.comment.converter.toEntity
import com.flata.instagram.domain.comment.model.Comment
import com.flata.instagram.domain.comment.model.Reply
import com.flata.instagram.domain.comment.repository.CommentRepository
import com.flata.instagram.domain.comment.repository.ReplyRepository
import com.flata.instagram.global.exception.comment.CommentNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommentService(
    private val commentRepository: CommentRepository,
    private val replyRepository: ReplyRepository
) {
    fun saveComment(
        commentDTO: CommentDTO
    ) {
        commentRepository.save(
            commentDTO.toEntity()
        )
    }

    fun saveReply(
        replyDTO: ReplyDTO
    ) {
        replyRepository.save(
            replyDTO.toEntity()
        )
    }

    @Transactional(readOnly = true)
    fun findCommentById(
        commentId: Long
    ): Comment = commentRepository.findByIdOrNull(commentId) ?: throw CommentNotFoundException()

    @Transactional(readOnly = true)
    fun findReplyById(
        replyId: Long
    ): Reply = replyRepository.findByIdOrNull(replyId) ?: throw CommentNotFoundException()

    fun deleteComment(
        commentId: Long
    ) {
        replyRepository.findByCommentId(commentId)
            .map { it.delete() }

        findCommentById(commentId).delete()
    }

    fun deleteReply(
        replyId: Long
    ) {
        findReplyById(replyId).delete()
    }
}