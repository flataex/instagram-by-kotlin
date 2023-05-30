package com.flata.instagram.domain.user.service

import com.flata.instagram.domain.user.controller.dto.SignInRequest
import com.flata.instagram.domain.user.controller.dto.SignInResponse
import com.flata.instagram.domain.user.controller.dto.SignUpRequest
import com.flata.instagram.domain.user.converter.toSignInResponse
import com.flata.instagram.domain.user.converter.toUser
import com.flata.instagram.domain.user.model.User
import com.flata.instagram.domain.user.repository.UserRepository
import com.flata.instagram.global.exception.user.UserNotFoundException
import com.flata.instagram.global.exception.user.WrongEmailException
import com.flata.instagram.global.exception.user.WrongPasswordException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import javax.validation.Valid

@Service
@Validated
@Transactional
class UserService(
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val userRepository: UserRepository
) {
    fun signUp(@Valid request: SignUpRequest) =
        userRepository.save(
            request.toUser()
        )

    @Transactional(readOnly = true)
    fun signIn(request: SignInRequest): SignInResponse =
        getUserBy(request.email)?.let { validateSignIn(request, it) }
            ?: throw WrongEmailException()

    private fun getUserBy(email: String): User? =
        userRepository.findByEmail(email)

    private fun validateSignIn(request: SignInRequest, user: User): SignInResponse =
        user.toSignInResponse().takeIf { bCryptPasswordEncoder.matches(request.password, user.password) }
            ?: throw WrongPasswordException()

    @Transactional(readOnly = true)
    fun existsByEmail(email: String): Boolean =
        userRepository.existsByEmail(email)

    @Transactional(readOnly = true)
    fun existsByNickname(nickname: String): Boolean =
        userRepository.existsByNickname(nickname)

    @Transactional(readOnly = true)
    fun getUserBy(userId: Long): User =
        userRepository.findById(userId).orElseThrow { UserNotFoundException() }
}
