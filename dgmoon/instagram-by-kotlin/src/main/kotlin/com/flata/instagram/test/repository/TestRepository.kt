package com.flata.instagram.test.repository

import com.flata.instagram.test.model.Test
import org.springframework.data.jpa.repository.JpaRepository

interface TestRepository : JpaRepository<Test, Long> {
}