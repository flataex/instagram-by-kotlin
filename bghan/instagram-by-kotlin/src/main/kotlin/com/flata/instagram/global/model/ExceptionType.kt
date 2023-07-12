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
    USER_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, "사용자를 조회할 수 없습니다."),

    // 게시글
    FEED_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, "해당 게시글을 조회할 수 없습니다."),
    NOT_FEED_OWNER_EXCEPTION(HttpStatus.BAD_REQUEST, "해당 게시글을 작성한 사용자가 아니라 삭제할 수 없습니다."),

    // 팔로우
    FOLLOW_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, "현재 팔로우 되어 있는 상태가 아닙니다."),

    // 좋아요
    LIKE_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, "현재 좋아요 되어 있는 상태가 아닙니다."),

    // 댓글
    COMMENT_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, "해당 댓글을 찾을 수 없습니다."),

    // Client
    BAD_REQUEST_BODY(HttpStatus.BAD_REQUEST, "유효하지 않은 요청입니다."),
    ILLEGAL_HTTP_METHOD(HttpStatus.BAD_REQUEST, "허용되지 않은 HTTP Method입니다."),

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