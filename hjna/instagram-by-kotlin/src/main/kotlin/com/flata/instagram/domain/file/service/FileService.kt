package com.flata.instagram.domain.file.service

import com.flata.instagram.domain.feed.model.entity.Feed
import com.flata.instagram.domain.file.entity.File
import com.flata.instagram.domain.file.repository.FileRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.transaction.Transactional

@Service
class FileService(private val fileRepository: FileRepository) {
    private val uploadDir: Path = Paths.get("./uploads")

    init {
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir)
        }
    }

    @Transactional
    fun saveFile(file: MultipartFile, feed: Feed): File {
        val filename = file.originalFilename ?: throw IllegalArgumentException("Invalid file name")
        val filePath = uploadDir.resolve(filename)
        file.transferTo(filePath.toFile())

        val fileEntity = File(url = filePath.toString(), feed = feed)
        return fileRepository.save(fileEntity)
    }
}