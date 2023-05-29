package com.flata.instagram.global

import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import java.util.*

fun MultipartFile.createFileName(): String {
    return UUID.randomUUID().toString() +
            "_" + LocalDateTime.now() +
            "_" + originalFilename
}