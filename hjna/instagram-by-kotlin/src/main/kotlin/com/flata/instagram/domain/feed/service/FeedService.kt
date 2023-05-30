package com.flata.instagram.domain.feed.service

import com.flata.instagram.domain.feed.model.dto.FeedRequest
import com.flata.instagram.domain.feed.model.entity.Feed
import com.flata.instagram.domain.feed.repository.FeedRepository
import com.flata.instagram.domain.file.service.FileService
import com.flata.instagram.domain.user.repository.UserRepository
import com.flata.instagram.global.utils.JwtProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class FeedService(
    private val feedRepository: FeedRepository,
    private val userRepository: UserRepository,
    private val fileService: FileService,
    private val jwtProvider: JwtProvider
) {

    @Transactional
    fun createFeed(jwt: String, feedRequest: FeedRequest, file: MultipartFile): Feed {
        val user = userRepository.findById(jwt.toLong()).orElseThrow { IllegalArgumentException("유효하지 않은 유저 정보입니다") }
        val feed = Feed(user = user, content = feedRequest.content)
        feedRepository.save(feed)
        fileService.saveFile(file, feed)
        return feed
    }

    @Transactional
    fun createFeed(jwt: String, feedRequest: FeedRequest): Feed {
        val user = userRepository.findByEmail(jwtProvider.getEmailFromJwtToken(jwt)).orElseThrow { IllegalArgumentException("유효하지 않은 유저 정보입니다") }
        val feed = Feed(user = user, content = feedRequest.content)
        feedRepository.save(feed)
        return feed
    }
}