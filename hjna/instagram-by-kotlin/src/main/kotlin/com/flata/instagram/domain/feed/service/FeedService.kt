package com.flata.instagram.domain.feed.service

import com.flata.instagram.domain.feed.model.dto.FeedRequest
import com.flata.instagram.domain.feed.model.dto.FeedResponse
import com.flata.instagram.domain.feed.model.entity.Feed
import com.flata.instagram.domain.feed.repository.FeedRepository
import com.flata.instagram.domain.file.service.FileService
import com.flata.instagram.domain.user.repository.UserRepository
import com.flata.instagram.global.utils.JwtProvider
import org.springframework.http.ResponseEntity
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
    fun createFeed(jwt: String, feedRequest: FeedRequest): Feed {
        val user = userRepository.findByEmail(jwtProvider.getEmailFromJwtToken(jwt)).orElseThrow { IllegalArgumentException("유효하지 않은 유저 정보입니다") }
        val feed = Feed(user = user, content = feedRequest.content)
        feedRepository.save(feed)
        if(feedRequest.images.isNotEmpty()){
            fileService.saveFile(feedRequest.images, feed);
        }
        return feed
    }

    /*
    fun findById(feedId: Long): ResponseEntity<FeedResponse> {
        return feedRepository.findById(feedId).get();
    }
    */
}