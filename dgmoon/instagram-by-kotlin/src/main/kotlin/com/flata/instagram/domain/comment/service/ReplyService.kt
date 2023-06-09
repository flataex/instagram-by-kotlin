package com.flata.instagram.domain.comment.service

import com.flata.instagram.domain.comment.dto.ReplyRequest
import com.flata.instagram.domain.comment.repository.ReplyRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReplyService(
    private val replyRepository: ReplyRepository
) {
    @Transactional
    fun saveReply(replyRequest: ReplyRequest) =
        replyRepository.save(replyRequest.toEntity())

    @Transactional
    fun updateReply(replyRequest: ReplyRequest) =
        replyRepository.findByIdOrNull(replyRequest.id)
            ?.update(replyRequest.content)
            ?: throw NoDataException()

    @Transactional
    fun deleteReply(replyRequest: ReplyRequest) =
        replyRepository.findByIdOrNull(replyRequest.id)
            ?.delete()
            ?: throw NoDataException()
}