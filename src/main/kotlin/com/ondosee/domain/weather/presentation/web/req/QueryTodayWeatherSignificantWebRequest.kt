package com.ondosee.domain.weather.presentation.web.req

import org.springframework.web.bind.annotation.RequestParam

data class QueryTodayWeatherSignificantWebRequest(
    @RequestParam val x: Double,
    @RequestParam val y: Double
)
