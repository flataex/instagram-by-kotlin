package com.flata.instagram.domain.user.service

import com.flata.instagram.domain.user.model.dto.UserRegisterRequest
import com.flata.instagram.domain.user.model.entity.User
import com.flata.instagram.domain.user.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserRepository) {

    private val passwordEncoder = BCryptPasswordEncoder()

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
                password = passwordEncoder.encode(userRequest.password),
                nickname = userRequest.nickname
        )
        return userRepository.save(user)
    }
}