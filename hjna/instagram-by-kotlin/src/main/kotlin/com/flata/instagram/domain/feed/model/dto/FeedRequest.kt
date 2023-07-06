package com.flata.instagram.domain.feed.model.dto

import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotBlank


data class FeedRequest(
    @field:NotBlank
    val content: String,
    val images: List<MultipartFile>,
)