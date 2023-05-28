package com.flata.instagram.domain.user.service

import com.flata.instagram.domain.user.controller.dto.UserLoginRequest
import com.flata.instagram.domain.user.controller.dto.UserSaveRequest
import com.flata.instagram.domain.user.model.User
import com.flata.instagram.domain.user.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    fun save(
        request: UserSaveRequest
    ): Long {
        validate(request)

        val user = User(
            email = request.email,
            password = bCryptPasswordEncoder.encode(request.password),
            nickname = request.nickname
        )

        return userRepository.save(user).id
    }

    private fun validate(
        request: UserSaveRequest
    ) {
        if (userRepository.existsByEmail(request.email)) {
            throw RuntimeException("중복된 이메일입니다.")
        }

        if (userRepository.existsByNickname(request.nickname)) {
            throw RuntimeException("중복된 닉네임입니다.")
        }
    }

    fun login(
        request: UserLoginRequest
    ): Long {
        val user = userRepository.findByEmail(request.email)
            ?: throw RuntimeException("존재하지 않는 계정입니다.")

        if (!bCryptPasswordEncoder.matches(request.password, user.password)) {
            throw RuntimeException("비밀번호가 일치하지 않습니다.")
        }

        return user.id
    }

}