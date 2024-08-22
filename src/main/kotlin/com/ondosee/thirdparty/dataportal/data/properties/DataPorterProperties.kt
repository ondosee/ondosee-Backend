package com.ondosee.thirdparty.dataportal.data.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "dataporter")
@ConstructorBinding
data class DataPorterProperties(
    val key: String
)