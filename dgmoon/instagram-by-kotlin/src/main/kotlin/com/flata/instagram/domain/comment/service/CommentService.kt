package com.flata.instagram.domain.comment.service

import com.flata.instagram.domain.comment.dto.CommentRequest
import com.flata.instagram.domain.comment.dto.CommentResponse
import com.flata.instagram.domain.comment.repository.CommentRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    @Transactional(readOnly = true)
    fun getComment(id: Long): CommentResponse =
        commentRepository.findByIdOrNull(id)
            ?.let {
                CommentResponse(
                    id = it.id,
                    feedId = it.feedId,
                    userId = it.userId,
                    content = it.content,
                    replies = it.replies ?: mutableListOf()
                )
            }
            ?: throw NoDataException()

    @Transactional
    fun saveComment(commentRequest: CommentRequest): Long =
        commentRepository.save(commentRequest.toEntity()).id

    @Transactional
    fun updateComment(commentRequest: CommentRequest) =
        commentRepository.findByIdOrNull(commentRequest.id)
            ?.update(commentRequest.content)
            ?: throw NoDataException()

    @Transactional
    fun deleteComment(commentRequest: CommentRequest) =
        commentRepository.findByIdOrNull(commentRequest.id)?.delete()
            ?: throw NoDataException()
}
