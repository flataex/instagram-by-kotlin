package com.flata.instagram.domain.file.dto

import org.jetbrains.annotations.NotNull
import org.springframework.web.multipart.MultipartFile

class FileRequest(
    val id: Long,
    @field:NotNull
    var url: String?,
    @field:NotNull
    var feedId: Long,
    @field:NotNull
    var file: MultipartFile?
)