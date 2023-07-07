package com.flata.instagram.domain.file.service

import com.flata.instagram.domain.file.dto.FileRequest
import com.flata.instagram.domain.file.dto.FileResponse
import com.flata.instagram.domain.file.model.File
import com.flata.instagram.domain.file.repository.FileRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.net.URI
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class FileService(
    private val fileRepository: FileRepository,
) {
    @Transactional(readOnly = true)
    fun getFile(id: Long): ResponseEntity<FileResponse> =
        fileRepository.findByIdOrNull(id)
            ?.let {
                ResponseEntity.ok(
                    FileResponse(
                        it.id,
                        it.url,
                        it.feedId
                    )
                )
            }
            ?: throw NoDataException()

    @Transactional
    fun saveFile(fileRequest: FileRequest, request: HttpServletRequest): ResponseEntity<Unit> {
        val realPath = request.servletContext.getRealPath("/")
        val fileName = UUID.randomUUID().toString()
            .plus(".")
            .plus(
                fileRequest.file?.originalFilename?.split(".")?.get(1)
            )
        val filePath = realPath.plus(
            "/".plus(
                fileName
            )
        )

        fileRequest.file?.transferTo(
            java.io.File(filePath)
        )

        return ResponseEntity.created(
            URI.create(
                "/files/".plus(
                    fileRepository.save(
                        File(
                            0,
                            "/".plus(fileName),
                            fileRequest.feedId
                        )
                    ).id
                )
            )
        ).build()
    }

    @Transactional
    fun deleteFile(fileRequest: FileRequest): ResponseEntity<Unit> =
        fileRepository.findByIdOrNull(fileRequest.id)
            ?.let {
                it.delete()
                ResponseEntity.noContent().build()
            }
            ?: throw NoDataException()

}