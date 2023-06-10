package com.flata.instagram.domain.login.service

import com.flata.instagram.domain.login.dto.LoginRequest
import com.flata.instagram.domain.user.repository.UserRepository
import com.flata.instagram.global.exception.InvalidLoginInfoException
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import javax.servlet.http.HttpSession

@Service
class LoginService(
    private val userRepository: UserRepository
) {
    fun login(loginRequest: LoginRequest, session: HttpSession): Long? {
        val user = userRepository.findByEmail(loginRequest.email) ?: throw InvalidLoginInfoException()
        val userId = user.id
        return userId.takeIf {
            BCrypt.checkpw(
                userRepository.findByEmail(loginRequest.email)!!
                    .password,
                loginRequest.password
            )
        }
    }

    fun logout(session: HttpSession) =
        session.invalidate()
}