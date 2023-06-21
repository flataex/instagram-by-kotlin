package com.flata.instagram.global.model

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.PrintWriter
import java.io.StringWriter

@Configuration
class JsonWriter {

    @Bean
    fun stringWriter(): StringWriter = StringWriter()

    @Bean
    fun printWriter(stringWriter: StringWriter) = PrintWriter(stringWriter)
}