package com.flata.instagram.domain.file.repository

import com.flata.instagram.domain.file.entity.File
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository:JpaRepository<File, Long> {
}