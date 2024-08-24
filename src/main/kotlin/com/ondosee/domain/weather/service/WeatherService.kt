package com.ondosee.domain.weather.service

import com.ondosee.domain.weather.presentation.data.req.QueryTodayWeatherSignificantRequestData
import com.ondosee.domain.weather.presentation.data.res.QueryTodayWeatherSignificantResponseData

interface WeatherService {
    fun queryTodayWeatherSignificant(request: QueryTodayWeatherSignificantRequestData): QueryTodayWeatherSignificantResponseData
}