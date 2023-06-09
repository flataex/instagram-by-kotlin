package com.flata.instagram.domain.feed.service

import com.flata.instagram.domain.comment.controller.dto.CommentResponse
import com.flata.instagram.domain.comment.repository.CommentRepository
import com.flata.instagram.domain.feed.controller.dto.FeedResponse
import com.flata.instagram.domain.feed.controller.dto.FeedSaveRequest
import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.domain.feed.repository.FeedRepository
import com.flata.instagram.domain.file.controller.dto.FileResponse
import com.flata.instagram.domain.file.model.File
import com.flata.instagram.domain.file.repository.FileRepository
import com.flata.instagram.domain.like.controller.dto.LikeResponse
import com.flata.instagram.domain.like.repository.LikeRepository
import com.flata.instagram.domain.reply.controller.dto.ReplyResponse
import com.flata.instagram.domain.reply.repository.ReplyRepository
import com.flata.instagram.domain.user.model.User
import com.flata.instagram.domain.user.repository.UserRepository
import com.flata.instagram.global.createFileName
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.nio.file.Files
import java.nio.file.Paths

@Service
@Transactional
class FeedService(
    private val userRepository: UserRepository,
    private val feedRepository: FeedRepository,
    private val commentRepository: CommentRepository,
    private val replyRepository: ReplyRepository,
    private val likeRepository: LikeRepository,
    private val fileRepository: FileRepository
) {

    @Value("\${file.path}")
    lateinit var filePath: String

    @Transactional(readOnly = true)
    fun findAll(
        userId: Long
    ): List<FeedResponse> = feedRepository.findByUserId(userId)
        .map { feed ->
            FeedResponse(
                content = feed.content,
                createdAt = feed.createdAt,
                comments = commentRepository.findByFeedId(feed.id)
                    .map { CommentResponse(
                        it.id,
                        it.user.nickname,
                        it.content,
                        replyRepository.findByCommentId(it.id)
                            .map { ReplyResponse(it.id, it.user.nickname, it.content) }
                    ) },
                likes = likeRepository.findByFeedId(feed.id)
                    .map { LikeResponse(it.user.nickname) },
                files = fileRepository.findByFeedId(feed.id)
                    .map { FileResponse(it.url) }
            )
        }

    @Transactional(readOnly = true)
    fun findById(
        feedId: Long
    ): FeedResponse = feedRepository.findByIdOrNull(feedId)
        ?.let { feed ->
            FeedResponse(
                content = feed.content,
                createdAt = feed.createdAt,
                comments = commentRepository.findByFeedId(feed.id)
                    .map { CommentResponse(
                        it.id,
                        it.user.nickname,
                        it.content,
                        replyRepository.findByCommentId(it.id)
                            .map { ReplyResponse(it.id, it.user.nickname, it.content) }
                    ) },
                likes = likeRepository.findByFeedId(feed.id)
                    .map { LikeResponse(it.user.nickname) },
                files = fileRepository.findByFeedId(feed.id)
                    .map { FileResponse(it.url) }
            )
        } ?: error("존재하지 않는 피드입니다.")

    fun save(
        userId: Long,
        request: FeedSaveRequest
    ): Long = userRepository.findByIdOrNull(userId)
        ?.let { user ->
            saveFeed(user, request)
                .also { saveImage(it, request) }
                .id
        } ?: error("존재하지 않는 계정입니다.")

    private fun saveFeed(
        user: User,
        request: FeedSaveRequest
    ): Feed = Feed(user = user, content = request.content)
        .let { feedRepository.save(it) }

    private fun saveImage(
        feed: Feed,
        request: FeedSaveRequest
    ) {
        request.images.forEach { it ->
            File(feed = feed, url = it.createFileName())
                .let { fileRepository.save(it) }

            Files.write(
                Paths.get(filePath + it.createFileName()),
                it.bytes
            )
        }
    }

}