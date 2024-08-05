package com.ondosee.thirdparty.vworld.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "vworld")
@ConstructorBinding
data class VWorldProperties(
    val key: String
)