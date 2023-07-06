package com.flata.instagram.domain.file.service

import com.flata.instagram.domain.feed.model.entity.Feed
import com.flata.instagram.domain.file.model.entity.File
import com.flata.instagram.domain.file.repository.FileRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.annotation.PostConstruct
import javax.transaction.Transactional

@Service
class FileService(private val fileRepository: FileRepository) {

    @Value("\${upload.path}")
    private lateinit var uploadPath: String

    private lateinit var uploadDir: Path

    @PostConstruct
    fun initUploadDir() {
        val realPath = Paths.get("").toAbsolutePath().toString()
        uploadDir = Paths.get(realPath, uploadPath)
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir)
        }
    }


    @Transactional
    fun saveFile(files: List<MultipartFile>, feed: Feed)  {
        for (file in files) {
            val filename = file.originalFilename ?: throw IllegalArgumentException("Invalid file name")
            val filePath = uploadDir.resolve(filename)
            file.transferTo(filePath.toFile())

            val fileEntity = File(url = filePath.toString(), feed = feed)
            fileRepository.save(fileEntity)
        }
    }

    fun getFilesByFeed(feed: Feed): List<String> {
        return fileRepository.findAllByFeed(feed).map { it.url }
    }
}