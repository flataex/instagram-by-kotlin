package com.flata.instagram.domain.file.controller

import com.flata.instagram.domain.file.dto.FileRequest
import com.flata.instagram.domain.file.dto.FileResponse
import com.flata.instagram.domain.file.service.FileService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
class FileController(
    private val fileService: FileService
) {
    @GetMapping
    fun getFiles(): ResponseEntity<List<FileResponse>> {
        return ResponseEntity.ok(fileService.getFiles())
    }

    @GetMapping("/{id}")
    fun getFiles(@PathVariable id: Long): ResponseEntity<FileResponse> {
        return ResponseEntity.ok(fileService.getFile(id))
    }

    @PostMapping
    fun getFile(@Valid @RequestBody fileRequest: FileRequest, request: HttpServletRequest): ResponseEntity<Any> {
        val savedFileId = fileService.saveFile(fileRequest, request)
        return ResponseEntity.created(URI.create("/files/".plus(savedFileId))).build()
    }

    @DeleteMapping
    fun deleteFile(@Valid @RequestBody fileRequest: FileRequest): ResponseEntity<Any> {
        fileService.deleteFile(fileRequest)
        return ResponseEntity.noContent().build()
    }
}