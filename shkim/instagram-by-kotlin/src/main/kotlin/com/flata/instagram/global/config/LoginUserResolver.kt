package com.flata.instagram.global.config

import com.flata.instagram.global.model.LoginUser
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

private const val BEARER = "Bearer"

@Component
class LoginUserResolver(): HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(LoginUser::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Long {
        return extractUserId(webRequest)
    }

    private fun extractUserId(
        request: NativeWebRequest
    ): Long {
        val authorization = request.getHeader(AUTHORIZATION) ?: throw IllegalArgumentException()
        val (tokenType, userId) = splitToTokenFormat(authorization)
        if (tokenType != BEARER) {
            throw IllegalArgumentException()
        }
        return userId
    }

    private fun splitToTokenFormat(
        authorization: String
    ): Pair<String, Long> {
        return try {
            val tokenFormat = authorization.split(" ")
            tokenFormat[0] to tokenFormat[1].toLong()
        } catch (e: IndexOutOfBoundsException) {
            throw IllegalArgumentException()
        }
    }
}