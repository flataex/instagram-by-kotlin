package com.flata.instagram.domain.file.service

import com.flata.instagram.domain.file.dto.FileRequest
import com.flata.instagram.domain.file.dto.FileResponse
import com.flata.instagram.domain.file.model.File
import com.flata.instagram.domain.file.repository.FileRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class FileService(
    private val fileRepository: FileRepository,
) {
    @Transactional(readOnly = true)
    fun getFiles(): List<FileResponse> {
        val files = fileRepository.findAll()
        return files.stream()
            .map { file ->
                FileResponse(
                    file.id,
                    file.url,
                    file.feedId
                )
            }.toList()

    }

    @Transactional(readOnly = true)
    fun getFile(id: Long): FileResponse {
        val file = fileRepository.findById(id)
            .orElseThrow {
                throw NoDataException()
            }
        return FileResponse(
            file.id,
            file.url,
            file.feedId
        )
    }

    @Transactional
    fun saveFile(fileRequest: FileRequest, request: HttpServletRequest): Long {
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

        return fileRepository.save(
            File(
                0,
                "/".plus(fileName),
                fileRequest.feedId
            )
        ).id
    }

    @Transactional
    fun deleteFile(fileRequest: FileRequest) {
        val fileToUpdate = fileRepository.findById(fileRequest.id)
            .orElseThrow { throw NoDataException() }
        fileToUpdate.delete()
    }
}