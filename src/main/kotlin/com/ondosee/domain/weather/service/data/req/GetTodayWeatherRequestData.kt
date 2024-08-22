package com.ondosee.domain.weather.service.data.req

data class GetTodayWeatherRequestData(
    val nx: Int,
    val ny: Int,
    val location: String
)
