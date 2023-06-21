package com.flata.instagram.domain.user.service

import com.flata.instagram.domain.user.controller.dto.UserLoginRequest
import com.flata.instagram.domain.user.controller.dto.UserSaveRequest
import com.flata.instagram.domain.user.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal class UserServiceTest {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `회원가입`() {
        // given
        val request = UserSaveRequest(
            email = "shkim@flataex.com",
            password = "1234",
            nickname = "뚱이"
        )

        // when
        val expectedId = userService.save(request)

        // then
        val actual = userRepository.findByIdOrNull(expectedId)
        assertEquals(expectedId, actual!!.id)
    }

    @Test
    fun `로그인()`() {
        // given
        val userSaveRequest = UserSaveRequest(
            email = "shkim@flataex.com",
            password = "1234",
            nickname = "뚱이"
        )
        val expectedId = userService.save(userSaveRequest)

        val userLoginRequest = UserLoginRequest(
            email = "shkim@flataex.com",
            password = "1234"
        )

        // when
        val actualId = userService.login(userLoginRequest)

        // then
        assertEquals(expectedId, actualId)
    }
}