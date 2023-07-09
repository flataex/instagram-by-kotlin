package com.flata.instagram.domain.login.service

import com.flata.instagram.domain.login.dto.LoginRequest
import com.flata.instagram.domain.user.repository.UserRepository
import com.flata.instagram.global.exception.InvalidLoginInfoException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.servlet.http.HttpSession

@Service
class LoginService(
    private val userRepository: UserRepository,
    private val encoder: BCryptPasswordEncoder
) {
    fun login(loginRequest: LoginRequest, session: HttpSession): Long =
        userRepository.findByEmail(loginRequest.email)
            ?.let {
                session.setAttribute("userId", it.id)
                it.id.takeIf {
                    encoder.matches(
                        userRepository.findByEmail(loginRequest.email)!!
                            .password,
                        loginRequest.password
                    )
                }
            }
            ?: throw InvalidLoginInfoException()

    fun logout(session: HttpSession) =
        session.invalidate()
}