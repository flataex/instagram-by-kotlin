package com.flata.instagram.domain.feed.controller.dto

import org.springframework.web.multipart.MultipartFile

data class FeedSaveRequest(
    val content: String,
    val images: List<MultipartFile>
)
