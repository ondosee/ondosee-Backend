package com.ondosee.thirdparty.openweather.data.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "openweather")
@ConstructorBinding
data class OpenWeatherProperties(
    val key: String
)