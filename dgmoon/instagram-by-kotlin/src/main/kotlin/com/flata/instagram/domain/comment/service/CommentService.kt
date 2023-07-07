package com.flata.instagram.domain.comment.service

import com.flata.instagram.domain.comment.dto.CommentRequest
import com.flata.instagram.domain.comment.dto.CommentResponse
import com.flata.instagram.domain.comment.repository.CommentRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.net.URI

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    @Transactional(readOnly = true)
    fun getComment(id: Long): ResponseEntity<CommentResponse> =
        commentRepository.findByIdOrNull(id)
            ?.let {
                ResponseEntity.ok(
                    CommentResponse(
                        id = it.id,
                        feedId = it.feedId,
                        userId = it.userId,
                        content = it.content,
                        replies = it.replies ?: mutableListOf()
                    )
                )
            }
            ?: throw NoDataException()

    @Transactional
    fun saveComment(commentRequest: CommentRequest): ResponseEntity<Unit> =
        commentRepository.save(commentRequest.toEntity())
            .let {
                ResponseEntity.created(
                    URI.create("/comments/".plus(it))
                ).build()
            }

    @Transactional
    fun updateComment(commentRequest: CommentRequest): ResponseEntity<Unit> =
        commentRepository.findByIdOrNull(commentRequest.id)
            ?.let {
                it.update(commentRequest.content)
                ResponseEntity.noContent().build()
            }
            ?: throw NoDataException()

    @Transactional
    fun deleteComment(commentRequest: CommentRequest): ResponseEntity<Unit> =
        commentRepository.findByIdOrNull(commentRequest.id)
            ?.let {
                it.delete()
                ResponseEntity.noContent().build()
            }
            ?: throw NoDataException()

}
