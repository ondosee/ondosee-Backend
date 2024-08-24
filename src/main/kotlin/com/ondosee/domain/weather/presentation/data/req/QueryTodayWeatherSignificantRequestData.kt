package com.ondosee.domain.weather.presentation.data.req

import org.springframework.web.bind.annotation.RequestParam

data class QueryTodayWeatherSignificantRequestData(
    @RequestParam val location: String,
    @RequestParam val x: String,
    @RequestParam val y: String
)
