package com.flata.instagram.domain.file.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.readAttributes
import kotlin.io.path.createFile
import java.nio.file.Files

@RestController
class FileController {
    @Value("\${upload.path}")
    private lateinit var uploadPath: String

    @GetMapping("/upload-files/{filename:.+}")
    fun serveFile(@PathVariable filename: String): ResponseEntity<Resource> {
        val file: Path = Paths.get(uploadPath).resolve(filename)

        val resource: Resource = runCatching {
            UrlResource(file.toUri())
        }.getOrElse {
            throw RuntimeException("Issue reading the file", it)
        }

        return if (resource.exists() || resource.isReadable) {
            val mimeType: String = runCatching {
                java.nio.file.Files.probeContentType(file)
            }.getOrElse {
                throw RuntimeException("Could not determine file type", it)
            } ?: "application/octet-stream"

            ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).body(resource)
        } else {
            throw RuntimeException("Could not read the file!")
        }
    }
}