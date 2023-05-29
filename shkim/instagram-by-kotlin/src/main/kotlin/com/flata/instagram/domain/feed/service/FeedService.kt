package com.flata.instagram.domain.feed.service

import com.flata.instagram.domain.feed.controller.dto.FeedResponse
import com.flata.instagram.domain.feed.controller.dto.FeedSaveRequest
import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.domain.feed.repository.FeedRepository
import com.flata.instagram.domain.file.controller.dto.FileResponse
import com.flata.instagram.domain.file.model.File
import com.flata.instagram.domain.file.repository.FileRepository
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
                    files = fileRepository.findByFeedId(feed.id)
                        .map { FileResponse(it.url) }
                )
            }

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