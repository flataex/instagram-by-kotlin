package com.flata.instagram.global.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(NoDataException::class)
    fun handleNoDataException(): ResponseEntity<Any> {
        return ResponseEntity.noContent().build()
    }

    @ExceptionHandler(NotUniqueColumnException::class)
    fun handleNotUniqueColumnException(): ResponseEntity<Any> {
        return ResponseEntity.internalServerError().build()
    }
}