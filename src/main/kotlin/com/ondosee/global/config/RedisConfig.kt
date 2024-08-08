package com.ondosee.global.config

import com.ondosee.common.cache.CacheKeyUtil
import com.ondosee.global.config.properties.RedisProperties
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer as fromSerializer

@EnableCaching
@Configuration
class RedisConfig(
    private val redisProperties: RedisProperties
) : CachingConfigurer {

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory(redisProperties.host, redisProperties.port)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>()
            .apply { keySerializer = StringRedisSerializer() }
            .apply { valueSerializer = Jackson2JsonRedisSerializer(String::class.java) }
            .apply { setConnectionFactory(redisConnectionFactory()) }
    }

    @Bean
    fun redisCacheManager(connectionFactory: RedisConnectionFactory): CacheManager {
        val redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(fromSerializer(StringRedisSerializer()))
            .serializeValuesWith(fromSerializer(GenericJackson2JsonRedisSerializer()))
            .entryTtl(Duration.ofDays(2))

        return RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(connectionFactory)
            .cacheDefaults(redisCacheConfiguration)
            .build()
    }

    @Bean
    fun cacheKeyUtil(): CacheKeyUtil {
        return CacheKeyUtil
    }
}