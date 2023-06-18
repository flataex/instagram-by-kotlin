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
    fun getFiles(): ResponseEntity<List<FileResponse>> =
        ResponseEntity.ok(fileService.getFiles())

    @GetMapping("/{id}")
    fun getFiles(@PathVariable id: Long): ResponseEntity<FileResponse> =
        ResponseEntity.ok(fileService.getFile(id))

    @PostMapping
    fun saveFile(@Valid @RequestBody fileRequest: FileRequest, request: HttpServletRequest): ResponseEntity<Unit> =
        ResponseEntity.created(
            fileService.saveFile(fileRequest, request).let {
                URI.create("/files/".plus(it))
            }
        ).build()

    @DeleteMapping
    fun deleteFile(@Valid @RequestBody fileRequest: FileRequest): ResponseEntity<Unit> {
        fileService.deleteFile(fileRequest)
        return ResponseEntity.noContent().build()
    }
}