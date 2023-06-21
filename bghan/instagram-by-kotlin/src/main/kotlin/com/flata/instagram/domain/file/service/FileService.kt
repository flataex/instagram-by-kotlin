package com.flata.instagram.domain.file.service

import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.domain.file.converter.toPathFrom
import com.flata.instagram.domain.file.model.File
import com.flata.instagram.domain.file.repository.FileRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.pathString

@Service
@Transactional
class FileService(
    private val fileRepository: FileRepository,
    @Value("\${file.root.path}") val fileRootPath: String
) {

    fun saveAll(files: List<MultipartFile>, feed: Feed) {
        files
            .forEach{ save(it, feed) }
    }

    private fun save(file:MultipartFile, feed: Feed) {
        val url: String = upload(file)
        savePath(url, feed)
    }

    private fun savePath(url: String, feed: Feed) {
        fileRepository.save(
            File(
                url = url,
                feed = feed
            )
        )
    }

    private fun upload(file: MultipartFile): String =
        Files.write(
            Paths.get(file.toPathFrom(fileRootPath)),
            file.bytes
        ).pathString

    @Transactional(readOnly = true)
    fun findBy(feed: Feed): List<String> =
        findAllBy(feed)
            .map { it.url }

    @Transactional(readOnly = true)
    fun findAllBy(feed: Feed): List<File> =
        fileRepository.findByFeed(feed)

    fun deleteBy(feed: Feed) =
        fileRepository.deleteAll(
            findAllBy(feed)
        )
}