package com.flata.instagram.domain.comment.service

import com.flata.instagram.domain.comment.dto.CommentRequest
import com.flata.instagram.domain.comment.dto.CommentResponse
import com.flata.instagram.domain.comment.repository.CommentRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun getComments(): List<CommentResponse> =
        commentRepository.findAll()
            .map {
                CommentResponse(
                    it.id,
                    it.feedId,
                    it.userId,
                    it.content,
                    it.replies ?: mutableListOf()
                )
            }

    fun getComment(id: Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(id) ?: throw NoDataException()

        return CommentResponse(
            id = comment.id,
            feedId = comment.feedId,
            userId = comment.userId,
            content = comment.content,
            replies = comment.replies ?: mutableListOf()
        )
    }

    fun saveComment(commentRequest: CommentRequest) =
        commentRepository.save(commentRequest.toEntity())

    fun updateComment(commentRequest: CommentRequest) {
        val comment = commentRepository.findByIdOrNull(commentRequest.id) ?: throw NoDataException()

        return comment.update(commentRequest.content)
    }

    fun deleteComment(commentRequest: CommentRequest) {
        val comment = commentRepository.findByIdOrNull(commentRequest.id) ?: throw NoDataException()
        comment.delete()
    }
}
