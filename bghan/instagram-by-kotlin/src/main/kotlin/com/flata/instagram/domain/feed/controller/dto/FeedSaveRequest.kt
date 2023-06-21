package com.flata.instagram.domain.feed.controller.dto

import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotBlank

data class FeedSaveRequest(
    @field:NotBlank
    val content: String,
    val images: List<MultipartFile>,
)
