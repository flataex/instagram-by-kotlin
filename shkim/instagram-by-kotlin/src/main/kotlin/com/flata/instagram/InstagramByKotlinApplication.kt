package com.flata.instagram

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class InstagramByKotlinApplication

fun main(args: Array<String>) {
    runApplication<InstagramByKotlinApplication>(*args)
}
