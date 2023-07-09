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
@RequestMapping("/files")
class FileController(
    private val fileService: FileService
) {
    @GetMapping
    fun getFiles(): ResponseEntity<List<FileResponse>> =
        ResponseEntity.ok(
            fileService.getFiles()
        )

    @GetMapping("/{id}")
    fun getFile(@PathVariable id: Long): ResponseEntity<FileResponse> =
        ResponseEntity.ok(
            fileService.getFile(id)
        )

    @PostMapping
    fun saveFile(@Valid @RequestBody fileRequest: FileRequest, request: HttpServletRequest): ResponseEntity<Unit> =
        fileService.saveFile(fileRequest, request)
            .let {
                ResponseEntity.created(
                    URI.create(
                        "/files/$it"
                    )
                ).build()
            }

    @DeleteMapping
    fun deleteFile(@Valid @RequestBody fileRequest: FileRequest): ResponseEntity<Unit> =
        run {
            fileService.deleteFile(fileRequest)
            ResponseEntity.noContent().build()
        }
}