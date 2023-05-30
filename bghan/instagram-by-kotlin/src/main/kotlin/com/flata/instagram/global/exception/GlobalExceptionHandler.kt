package com.flata.instagram.global.exception

import com.flata.instagram.global.converter.ExceptionConverter
import com.flata.instagram.global.converter.toResponseEntity
import com.flata.instagram.global.model.ErrorResponse
import com.flata.instagram.global.model.ExceptionType
import com.flata.instagram.global.model.toResponseEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLIntegrityConstraintViolationException

@RestControllerAdvice
class GlobalExceptionHandler(
    val exceptionConverter: ExceptionConverter
) {
    val log: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    fun sqlIntegrityConstraintViolationException(exception: SQLIntegrityConstraintViolationException): ResponseEntity<ErrorResponse> {
        log.error(exceptionConverter.convertStackTraceAsStringFrom(exception))
        return ExceptionType.DUPLICATE_KEY_EXCEPTION.toResponseEntity()
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun internalServerError(exception: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        log.error(exceptionConverter.convertStackTraceAsStringFrom(exception))
        return exception.toResponseEntity()
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(exception: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        log.error(exceptionConverter.convertStackTraceAsStringFrom(exception))
        return ExceptionType.BAD_REQUEST_BODY.toResponseEntity()
    }

    @ExceptionHandler(ApplicationException::class)
    fun applicationException(exception: ApplicationException): ResponseEntity<ErrorResponse> {
        log.error(exceptionConverter.convertStackTraceAsStringFrom(exception))
        return exception.toResponseEntity()
    }

    @ExceptionHandler(Exception::class)
    fun internalServerError(exception: Exception): ResponseEntity<ErrorResponse> {
        log.error(exceptionConverter.convertStackTraceAsStringFrom(exception))
        return ExceptionType.INTERNAL_SERVER_ERROR.toResponseEntity()
    }
}