package com.ondosee.common.spi.weather

import com.ondosee.common.spi.weather.data.req.GetTodayWeatherRequestData
import com.ondosee.common.spi.weather.data.res.GetTodayWeatherResponseData

interface WeatherPort {
    fun getTodayWeather(request: GetTodayWeatherRequestData): List<GetTodayWeatherResponseData>
}