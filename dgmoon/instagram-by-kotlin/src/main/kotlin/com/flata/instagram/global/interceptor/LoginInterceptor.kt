package com.flata.instagram.global.interceptor

import com.flata.instagram.domain.user.repository.UserRepository
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoginInterceptor(
    private val userRepository: UserRepository
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (request.method == HttpMethod.GET.name) {
            return true
        }
        val authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        val userId = authorizationHeader.substring(
            authorizationHeader.length - 1
        )

        return userRepository.existsById(userId.toLong())
    }
}