package com.flata.instagram.domain.feed.service

import com.flata.instagram.domain.feed.controller.dto.FeedSaveRequest
import com.flata.instagram.domain.feed.repository.FeedRepository
import com.flata.instagram.domain.user.controller.dto.UserSaveRequest
import com.flata.instagram.domain.user.service.UserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal class FeedServiceTest {

    @Autowired
    lateinit var feedService: FeedService

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var feedRepository: FeedRepository

    @Test
    fun `피드 리스트 조회`() {
        // given
        val savedUserId = userService.save(
            UserSaveRequest(
                email = "shkim@flataex.com",
                password = "1234",
                nickname = "뚱이"
            )
        )

        val feedSaveRequest = FeedSaveRequest(
            "테스트",
            emptyList()
        )

        feedService.save(savedUserId, feedSaveRequest)

        // when
        val feedResponses = feedService.findAll(savedUserId)

        // then
        assertEquals(feedSaveRequest.content, feedResponses.get(0).content)
    }

    @Test
    fun `피드 단건 조회`() {
        // given
        val savedUserId = userService.save(
            UserSaveRequest(
                email = "shkim@flataex.com",
                password = "1234",
                nickname = "뚱이"
            )
        )

        val feedSaveRequest = FeedSaveRequest(
            "테스트",
            emptyList()
        )

        val savedFeedId = feedService.save(savedUserId, feedSaveRequest)

        // when
        val feedResponse = feedService.findById(savedFeedId)

        // then
        assertEquals(feedSaveRequest.content, feedResponse.content)
    }

    @Test
    fun `피드 등록`() {
        // given
        val savedUserId = userService.save(
            UserSaveRequest(
                email = "shkim@flataex.com",
                password = "1234",
                nickname = "뚱이"
            )
        )

        val feedSaveRequest = FeedSaveRequest(
            "테스트",
            emptyList()
        )

        // when
        val expectedId = feedService.save(savedUserId, feedSaveRequest)

        // then
        val actualId = feedRepository.findByIdOrNull(expectedId)
        assertEquals(expectedId, actualId!!.id)
    }
}