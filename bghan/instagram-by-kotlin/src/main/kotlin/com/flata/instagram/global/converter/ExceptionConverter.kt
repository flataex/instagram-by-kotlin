package com.flata.instagram.global.converter

import com.flata.instagram.global.exception.ApplicationException
import com.flata.instagram.global.model.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import java.io.PrintWriter
import java.io.StringWriter
import javax.validation.ConstraintViolationException
import javax.validation.ValidationException

@Component
class ExceptionConverter(
    private val stringWriter: StringWriter,
    private val printWriter: PrintWriter
) {
    fun convertStackTraceAsStringFrom(exception: Exception): String =
        stringWriter
            .also { exception.printStackTrace(printWriter) }
            .toString()
            .trim()
}

fun ValidationException.toResponseEntity(): ResponseEntity<ErrorResponse> = ResponseEntity(
    ErrorResponse(this.cause?.message.orEmpty()),
    HttpStatus.BAD_REQUEST
)

fun MethodArgumentNotValidException.toResponseEntity(): ResponseEntity<ErrorResponse> = ResponseEntity.badRequest()
    .body(this.convertsToValidationMessage())

private fun MethodArgumentNotValidException.convertsToValidationMessage(): ErrorResponse =
    this.fieldErrors[0]
        .defaultMessage?.let { ErrorResponse(message = it) }!!

fun ApplicationException.toResponseEntity(): ResponseEntity<ErrorResponse> = ResponseEntity(
    ErrorResponse(this.message.orEmpty()),
    this.httpStatus
)

fun ConstraintViolationException.toResponseEntity(): ResponseEntity<ErrorResponse> = ResponseEntity(
    ErrorResponse(this.message.orEmpty()),
    HttpStatus.BAD_REQUEST
)