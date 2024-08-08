package com.ondosee.global.config

import com.ondosee.thirdparty.vworld.properties.VWorldProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        VWorldProperties::class
    ]
)
class PropertiesScanConfig