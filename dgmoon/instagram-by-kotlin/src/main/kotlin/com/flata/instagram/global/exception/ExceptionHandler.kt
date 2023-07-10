package com.flata.instagram.global.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(NoDataException::class)
    fun handleNoDataException(): ResponseEntity<Unit> =
        ResponseEntity.noContent().build()

    @ExceptionHandler(NotUniqueColumnException::class)
    fun handleNotUniqueColumnException(): ResponseEntity<Unit> =
        ResponseEntity(HttpStatus.CONFLICT)

    @ExceptionHandler(InvalidLoginInfoException::class)
    fun handleInvalidLoginInfoException(): ResponseEntity<Unit> =
        ResponseEntity(HttpStatus.UNAUTHORIZED)

    @ExceptionHandler(RedisException::class)
    fun handleRedisException(): ResponseEntity<Unit> =
        ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
}