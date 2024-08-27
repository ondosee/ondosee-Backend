package com.ondosee.thirdparty.openweather.mapper

import com.ondosee.domain.airquality.service.data.res.GetTodayAirQualityResponseData
import com.ondosee.domain.airquality.service.data.res.GetTodayAirQualityResponseData.TimeZoneResponseData
import com.ondosee.domain.weather.service.data.enums.Element
import com.ondosee.thirdparty.openweather.data.web.GetTodayAirQualityOpenWeatherWebResponse
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun GetTodayAirQualityOpenWeatherWebResponse.toResponse(): List<GetTodayAirQualityResponseData> {
    val pm25 = mutableListOf<TimeZoneResponseData>()
    val pm10 = mutableListOf<TimeZoneResponseData>()

    list.map {

        val time = LocalDateTime.ofInstant(Instant.ofEpochSecond(it.timestamp),
            ZoneId.systemDefault()).toLocalTime()

        TimeZoneResponseData(
            time = time,
            value = it.components.pm10
        ).run(pm10::add)

        TimeZoneResponseData(
            time = time,
            value = it.components.pm25
        ).run(pm25::add)
    }

    return listOf(
        GetTodayAirQualityResponseData(
            element = Element.PARTICULATE_MATTER_10,
            value = pm10
        ),
        GetTodayAirQualityResponseData(
            element = Element.PARTICULATE_MATTER_25,
            value = pm25
        )
    )
}