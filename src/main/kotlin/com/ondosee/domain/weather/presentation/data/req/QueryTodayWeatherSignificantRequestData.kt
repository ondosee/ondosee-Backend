package com.ondosee.domain.weather.presentation.data.req

import org.springframework.web.bind.annotation.RequestParam

data class QueryTodayWeatherSignificantRequestData(
    @RequestParam val x: Double,
    @RequestParam val y: Double
)
