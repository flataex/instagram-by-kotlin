package com.flata.instagram.test.service

import com.flata.instagram.test.repository.TestRepository
import com.flata.instagram.test.repository.TestRepository2
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class TestService2(
    private val testRepository: TestRepository,
    private val testRepository2: TestRepository2
) {
    @KafkaListener(topics = ["string"], groupId = "test")
    fun test4(message: String): String {
        println("message = ${message}")
        return "I hear you"
    }
}