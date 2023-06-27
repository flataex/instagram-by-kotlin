package com.flata.instagram.domain.user.service

import com.flata.instagram.domain.user.model.dto.UserLoginRequest
import com.flata.instagram.domain.user.model.dto.UserRegisterRequest
import com.flata.instagram.domain.user.model.entity.User
import com.flata.instagram.domain.user.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class UserService(private val userRepository: UserRepository
    ) {

    @Transactional
    fun registerUser(userRequest: UserRegisterRequest): User {
        if (userRepository.existsByEmail(userRequest.email)) {
            throw RuntimeException("이미 존재하는 이메일입니다.")
        }
        if (userRepository.existsByNickname(userRequest.nickname)) {
            throw RuntimeException("이미 존재하는 닉네임입니다.")
        }
        val user = User(
                email = userRequest.email,
                password = BCryptPasswordEncoder().encode(userRequest.password),
                nickname = userRequest.nickname
        )
        return userRepository.save(user)
    }

    @Transactional
    fun login(loginRequest: UserLoginRequest): User {
        val user = userRepository.findByEmail(loginRequest.email).orElseThrow { IllegalArgumentException("이메일이 존재하지 않습니다") }

        if (!BCryptPasswordEncoder().matches(loginRequest.password, user.password)) {
            throw IllegalArgumentException("비밀번호가 틀렸습니다.")
        }

        return user
    }

}