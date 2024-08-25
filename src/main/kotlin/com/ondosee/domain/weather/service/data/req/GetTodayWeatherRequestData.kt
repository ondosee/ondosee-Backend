package com.ondosee.domain.weather.service.data.req

data class GetTodayWeatherRequestData(
    val x: Long,
    val y: Long,
    val location: String
)
