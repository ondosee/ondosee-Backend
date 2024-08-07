package com.ondosee.thirdparty.vworld

import com.ondosee.thirdparty.vworld.data.web.SearchDistrictVWoldWebResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "VWorldClient",
    url = "https://api.vworld.kr"
)
interface VWorldClient {
    @GetMapping(value = ["/req/search"])
    fun searchDistrict(
        @RequestParam query: String,
        @RequestParam size: Int,
        @RequestParam page: Int,
        @RequestParam type: String,
        @RequestParam category: String,
        @RequestParam request: String,
        @RequestParam key: String
    ): SearchDistrictVWoldWebResponse
}