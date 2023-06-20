package com.flata.instagram.domain.file.dto

import org.jetbrains.annotations.NotNull
import org.springframework.web.multipart.MultipartFile

data class FileRequest(
    val id: Long,
    @field:NotNull
    val url: String?,
    @field:NotNull
    val feedId: Long,
    @field:NotNull
    val file: MultipartFile?
)