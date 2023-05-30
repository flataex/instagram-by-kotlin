package com.flata.instagram.domain.login.service

import com.flata.instagram.domain.login.dto.LoginRequest
import com.flata.instagram.domain.login.dto.LoginSession
import com.flata.instagram.domain.user.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import javax.servlet.http.HttpSession

@Service
class LoginService(
    private val userRepository: UserRepository
) {
    fun login(loginRequest: LoginRequest, session: HttpSession): Long? {
        val user = userRepository.findByEmail(loginRequest.email)
        val isValid = BCrypt.checkpw(
            loginRequest.password,
            user.password
        )

        if (isValid) {
            val loginSession = LoginSession(loginRequest.email)
            session.setAttribute(
                "session",
                loginSession
            )
            return user.id
        }

        return null
    }

    fun logout(session: HttpSession) {
        session.invalidate()
    }
}