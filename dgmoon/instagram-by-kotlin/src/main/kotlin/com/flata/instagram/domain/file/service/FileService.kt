package com.flata.instagram.domain.file.service

import com.flata.instagram.domain.file.dto.FileRequest
import com.flata.instagram.domain.file.dto.FileResponse
import com.flata.instagram.domain.file.model.File
import com.flata.instagram.domain.file.repository.FileRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class FileService(
    private val fileRepository: FileRepository,
) {
    @Transactional(readOnly = true)
    fun getFiles(): List<FileResponse> =
        fileRepository.findAll()
            .filter {
                it.deletedAt == null
            }
            .map {
                FileResponse(
                    it.id,
                    it.url,
                    it.feedId
                )
            }.toList()

    @Transactional(readOnly = true)
    fun getFile(id: Long): FileResponse =
        fileRepository.findByIdOrNull(id)
            ?.let {
                FileResponse(
                    it.id,
                    it.url,
                    it.feedId
                )
            }
            ?: throw NoDataException()

    @Transactional
    fun saveFile(fileRequest: FileRequest, request: HttpServletRequest): Long =
        run {
            val fileName = "${UUID.randomUUID()}.${fileRequest.file.originalFilename?.split(".")?.get(1)}"
            fileRequest.file.transferTo(
                java.io.File("${request.servletContext.getRealPath("/")}/$fileName")
            )

            fileRepository.save(
                File(
                    0,
                    "/".plus(fileName),
                    fileRequest.feedId
                )
            ).id
        }

    @Transactional
    fun deleteFile(fileId: Long) =
        fileRepository.findByIdOrNull(fileId)
            ?.delete()
            ?: throw NoDataException()

}