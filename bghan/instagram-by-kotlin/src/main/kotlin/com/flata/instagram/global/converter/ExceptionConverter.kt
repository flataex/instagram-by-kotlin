package com.flata.instagram.global.converter

import com.flata.instagram.global.exception.ApplicationException
import com.flata.instagram.global.model.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import java.io.PrintWriter
import java.io.StringWriter

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

fun MethodArgumentNotValidException.toResponseEntity(): ResponseEntity<ErrorResponse> = ResponseEntity.badRequest()
    .body(this.convertsToValidationMessage())

private fun MethodArgumentNotValidException.convertsToValidationMessage(): ErrorResponse =
    this.fieldErrors[0]
        .defaultMessage?.let { ErrorResponse(message = it) }!!

fun ApplicationException.toResponseEntity(): ResponseEntity<ErrorResponse> = ResponseEntity(
    ErrorResponse(this.message.orEmpty()),
    this.httpStatus
)