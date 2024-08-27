package com.ondosee.common.spi

import com.ondosee.domain.weather.service.data.req.GetTodayWeatherRequestData
import com.ondosee.domain.weather.service.data.res.GetTodayWeatherResponseData

interface WeatherPort {
    fun getTodayWeather(request: GetTodayWeatherRequestData): List<GetTodayWeatherResponseData>
}