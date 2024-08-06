package com.ondosee.global.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@EnableFeignClients
@Configuration
class FeignConfig {

    @Bean
    fun feignDecoder(): Decoder {
        return JacksonDecoder()
    }

    @Bean
    fun feignEncoder(): Encoder {
        return JacksonEncoder()
    }
}