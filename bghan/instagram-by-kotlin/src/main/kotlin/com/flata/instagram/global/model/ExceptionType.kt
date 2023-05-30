package com.flata.instagram.global.model

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

enum class ExceptionType(
    val httpStatus: HttpStatus,
    val message: String
) {
    // 사용자
    WRONG_EMAIL_EXCEPTION(HttpStatus.BAD_REQUEST, "존재하는 이메일이 없습니다."),
    WRONG_PASSWORD_EXCEPTION(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다."),

    // Client
    BAD_REQUEST_BODY(HttpStatus.BAD_REQUEST, "유효하지 않은 요청입니다."),

    // DB
    DUPLICATE_KEY_EXCEPTION(HttpStatus.BAD_REQUEST, "이미 저장된 데이터가 존재합니다."),

    // Server
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다.")
}

fun ExceptionType.toResponseEntity() = ResponseEntity(
    this.message.toErrorResponse(),
    this.httpStatus
)

private fun String.toErrorResponse() = ErrorResponse(
    message = this
)