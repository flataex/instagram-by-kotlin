package com.flata.instagram.domain.file.converter

import org.springframework.web.multipart.MultipartFile
import java.util.UUID

fun MultipartFile.toPathFrom(rootPath: String): String =
    rootPath + "/" + UUID.randomUUID().toString() +
            "_" + this.originalFilename