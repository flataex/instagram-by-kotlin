package com.flata.instagram.test.config

import com.fasterxml.jackson.databind.JsonSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaConfig {
    @Bean
    fun kafkaCustomTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(kafkaCustomProducerFactory())
    }

    @Bean
    fun kafkaCustomProducerFactory(): ProducerFactory<String, String> {
        val config: HashMap<String, Any> = HashMap()
        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return DefaultKafkaProducerFactory(config)
    }

}