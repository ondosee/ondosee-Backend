package com.ondosee.domain.weather.presentation

import com.ondosee.domain.weather.presentation.data.req.QueryTodayWeatherSignificantRequestData
import com.ondosee.domain.weather.presentation.data.res.QueryTodayWeatherSignificantResponseData
import com.ondosee.domain.weather.service.WeatherService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/weather")
class WeatherController(
    private val weatherService: WeatherService
) {
    @GetMapping("/significant")
    fun queryTodayWeatherSignificant(
        webRequest: QueryTodayWeatherSignificantRequestData
    ): ResponseEntity<QueryTodayWeatherSignificantResponseData> =
        weatherService.queryTodayWeatherSignificant(webRequest)
            .run(ResponseEntity.status(HttpStatus.OK)::body)
}