package com.ondosee.thirdparty.openweather.client

import com.ondosee.thirdparty.openweather.data.web.GetTodayAirQualityOpenWeatherWebResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "OpenWeatherClient",
    url = "http://api.openweathermap.org"
)
interface OpenWeatherClient {
    @GetMapping("/data/2.5/air_pollution/history")
    fun getTodayAirQuality(
        @RequestParam lat: Double,
        @RequestParam lon: Double,
        @RequestParam start: Long,
        @RequestParam end: Long,
        @RequestParam appid: String,
    ): GetTodayAirQualityOpenWeatherWebResponse
}