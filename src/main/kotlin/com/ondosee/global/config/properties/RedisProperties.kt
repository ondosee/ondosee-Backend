package com.ondosee.global.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "spring.redis")
@ConstructorBinding
data class RedisProperties(
    val host: String,
    val port: Int
)
