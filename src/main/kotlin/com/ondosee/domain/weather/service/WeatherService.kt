package com.ondosee.domain.weather.service

import com.ondosee.domain.weather.presentation.web.req.QueryTodayWeatherSignificantWebRequest
import com.ondosee.domain.weather.presentation.web.res.QueryTodayWeatherSignificantWebResponse

interface WeatherService {
    fun queryTodayWeatherSignificant(request: QueryTodayWeatherSignificantWebRequest): QueryTodayWeatherSignificantWebResponse
}