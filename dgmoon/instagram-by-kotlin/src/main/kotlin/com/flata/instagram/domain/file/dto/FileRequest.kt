package com.flata.instagram.domain.file.dto

import org.jetbrains.annotations.NotNull
import org.springframework.web.multipart.MultipartFile

class FileRequest(
    val id: Long,
    @NotNull
    var url: String?,
    @NotNull
    var feedId: Long,
    @NotNull
    var file: MultipartFile?
)