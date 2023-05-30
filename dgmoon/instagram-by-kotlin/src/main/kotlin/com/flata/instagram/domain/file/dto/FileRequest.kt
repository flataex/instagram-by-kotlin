package com.flata.instagram.domain.file.dto

import org.springframework.web.multipart.MultipartFile

class FileRequest(
    val id: Long,
    var url: String?,
    var feedId: Long,
    var file: MultipartFile?
)