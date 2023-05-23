package com.flata.instagram.test.service

import com.flata.instagram.test.model.Test
import com.flata.instagram.test.model.Test2
import com.flata.instagram.test.repository.TestRepository
import com.flata.instagram.test.repository.TestRepository2
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class TestService(
        private val testRepository: TestRepository,
        private val testRepository2: TestRepository2,
        private val kafkaTemplate: KafkaTemplate<String, String>
) {
    fun test(): String {
        var test = Test()
        test.id = 1L
        test.name = "test"
        testRepository.save(test)
        val result = testRepository.findById(1L).get().name.toString()

        return result;
    }

    fun test2(): String {
        var test2 = Test2()
        test2.id = 1L
        test2.name = "test2"
        testRepository2.save(test2)
        val result2 = testRepository2.findById(1L).get().name.toString()
        return result2
    }

    fun test3(): String {
        kafkaTemplate.send("string", "test0")
        return "kafka string produced"
    }
}