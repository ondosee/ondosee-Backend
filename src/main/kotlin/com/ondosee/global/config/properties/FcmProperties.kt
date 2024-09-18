package com.ondosee.global.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "fcm")
@ConstructorBinding
class FcmProperties(
    val url: String
)
