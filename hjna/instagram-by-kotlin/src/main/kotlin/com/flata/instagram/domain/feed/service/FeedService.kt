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
        val user = userRepository.findById(jwtProvider.getIdFromJwtToken(jwt)).orElseThrow { IllegalArgumentException("유효하지 않은 유저 정보입니다") }
        val feed = Feed(user = user, content = feedRequest.content)
        feedRepository.save(feed)
        if(feedRequest.images.isNotEmpty()){
            fileService.saveFile(feedRequest.images, feed);
        }
        return feed
    }

    @Transactional
    fun getFeedById(feedId: Long): FeedResponse {
        val feed = feedRepository.findById(feedId).orElseThrow { IllegalArgumentException("존재하지 않는 피드입니다.") }

        return FeedResponse(feed)
    }

    @Transactional
    fun deleteFeed(jwt: String, feedId: Long) {
        val user = userRepository.findById(jwtProvider.getIdFromJwtToken(jwt)).orElseThrow { IllegalArgumentException("유효하지 않은 유저 정보입니다") }
        val feed = feedRepository.findById(feedId).orElseThrow { IllegalArgumentException("존재하지 않는 피드입니다.") }
        if (feed.user != user) {
            throw IllegalArgumentException("해당 유저는 이 피드를 삭제할 권한이 없습니다.")
        }
        feedRepository.delete(feed)
    }

    @Transactional
    fun updateFeed(jwt: String, feedId: Long, feedUpdateRequest: FeedRequest): FeedResponse {
        val user = userRepository.findById(jwtProvider.getIdFromJwtToken(jwt)).orElseThrow { IllegalArgumentException("유효하지 않은 유저 정보입니다") }
        val feed = feedRepository.findById(feedId).orElseThrow { IllegalArgumentException("존재하지 않는 피드입니다.") }
        if (feed.user != user) {
            throw IllegalArgumentException("해당 유저는 이 피드를 수정할 권한이 없습니다.")
        }
        feed.content = feedUpdateRequest.content

        if(feedUpdateRequest.images.isNotEmpty()){
            fileService.saveFile(feedUpdateRequest.images, feed);
        }
        feedRepository.save(feed)
        return FeedResponse(feed)
    }
}