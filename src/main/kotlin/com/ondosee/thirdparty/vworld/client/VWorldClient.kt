package com.ondosee.thirdparty.vworld.client

import com.ondosee.thirdparty.vworld.data.web.SearchDistrictVWorldWebResponse
import org.springframework.cache.annotation.Cacheable
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "VWorldClient",
    url = "https://api.vworld.kr"
)
interface VWorldClient {
    @Cacheable(
        value = ["SearchDistrictVWoldWebResponse"],
        key = "@cacheKeyUtil.generateCacheKey(#query, #page)",
        cacheManager = "redisCacheManager"
    )
    @GetMapping(value = ["/req/search"])
    fun searchDistrict(
        @RequestParam query: String,
        @RequestParam size: Int,
        @RequestParam page: Int,
        @RequestParam type: String,
        @RequestParam category: String,
        @RequestParam request: String,
        @RequestParam key: String
    ): SearchDistrictVWorldWebResponse
}

