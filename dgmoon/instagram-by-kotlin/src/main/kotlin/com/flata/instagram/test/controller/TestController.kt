package com.flata.instagram.test.controller

import com.flata.instagram.test.service.TestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    val testService: TestService
) {
    @GetMapping("/test")
    fun test(): String {
        return testService.test()
    }

    @GetMapping("/test2")
    fun test2(): String {
        return testService.test2()
    }

    @GetMapping("/test3")
    fun test3(): String {
        return testService.test3()
    }
}