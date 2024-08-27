package com.ondosee.thirdparty.dataportal.client

import com.ondosee.thirdparty.dataportal.data.web.GetTodayWeatherDataPorterWebResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "DataPortalClient",
    url = "http://apis.data.go.kr"
)
interface DataPortalClient {
    @GetMapping("/1360000/VilageFcstInfoService_2.0/getVilageFcst")
    fun getTodayWeathers(
        @RequestParam numOfRows: Int,
        @RequestParam pageNo: Int,
        @RequestParam dataType: String,
        @RequestParam base_date: String,
        @RequestParam base_time: String,
        @RequestParam nx: Int,
        @RequestParam ny: Int,
        @RequestParam serviceKey: String
    ) : GetTodayWeatherDataPorterWebResponse
}