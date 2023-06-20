package com.flata.instagram.domain.file.service

import com.flata.instagram.domain.file.dto.FileRequest
import com.flata.instagram.domain.file.dto.FileResponse
import com.flata.instagram.domain.file.model.File
import com.flata.instagram.domain.file.repository.FileRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.UUID
import javax.servlet.http.HttpServletRequest

@Service
class FileService(
        private val fileRepository: FileRepository,
) {
    @Transactional(readOnly = true)
    fun getFiles(): List<FileResponse> =
            fileRepository.findAll()
                    .stream()
                    .map { file ->
                        FileResponse(
                                file.id,
                                file.url,
                                file.feedId
                        )
                    }.toList()

    @Transactional(readOnly = true)
    fun getFile(id: Long): FileResponse =
            fileRepository.findById(id)
                    .orElseThrow {
                        throw NoDataException()
                    }.let {
                        FileResponse(
                                it.id,
                                it.url,
                                it.feedId
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
    fun deleteFile(fileRequest: FileRequest) =
        fileRepository.findById(fileRequest.id)
                .orElseThrow { throw NoDataException() }
                .delete()
}