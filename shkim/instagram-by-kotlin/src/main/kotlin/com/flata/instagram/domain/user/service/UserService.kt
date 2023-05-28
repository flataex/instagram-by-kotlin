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

    fun save(request: UserSaveRequest): Long {
        return request.validateEmail()
            .validateNickname()
            .createUser()
            .saveUser()
            .id
    }

    private fun UserSaveRequest.validateEmail(): UserSaveRequest {
        require(!userRepository.existsByEmail(email)) { "중복된 이메일입니다." }
        return this
    }

    private fun UserSaveRequest.validateNickname(): UserSaveRequest {
        require(!userRepository.existsByNickname(nickname)) { "중복된 닉네임입니다." }
        return this
    }

    private fun UserSaveRequest.createUser(): User {
        return User(
            email = email,
            password = bCryptPasswordEncoder.encode(password),
            nickname = nickname
        )
    }

    private fun User.saveUser(): User {
        return userRepository.save(this)
    }

    fun login(
        request: UserLoginRequest
    ): Long = userRepository.findByEmail(request.email)?.run {
        require(bCryptPasswordEncoder.matches(request.password, password)) { "비밀번호가 일치하지 않습니다." }
        id
    } ?: error("존재하지 않는 계정입니다.")

}