package com.flata.instagram.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class RedisConfig {
    @Bean
    fun redisTemplate(): RedisTemplate<String, String> {
        return RedisTemplate()
    }
}