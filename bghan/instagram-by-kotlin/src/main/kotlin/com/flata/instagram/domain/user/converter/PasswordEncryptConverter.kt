package com.flata.instagram.domain.user.converter

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.AttributeConverter

class PasswordEncryptConverter(
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
): AttributeConverter<String, String> {
    override fun convertToDatabaseColumn(entityAttribute: String?): String = bCryptPasswordEncoder.encode(entityAttribute)
    override fun convertToEntityAttribute(dbData: String?): String? = dbData
}