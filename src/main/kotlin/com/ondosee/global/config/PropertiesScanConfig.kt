package com.ondosee.global.config

import com.ondosee.global.config.properties.RedisProperties
import com.ondosee.thirdparty.vworld.data.properties.VWorldProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        VWorldProperties::class,
        RedisProperties::class
    ]
)
class PropertiesScanConfig