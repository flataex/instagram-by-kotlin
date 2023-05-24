package com.flata.instagram.test.model

import org.springframework.data.redis.core.RedisHash
import javax.persistence.Id

@RedisHash(value = "test2")
class Test2 {
    @Id
    var id: Long? = null
    var name: String? = null
}