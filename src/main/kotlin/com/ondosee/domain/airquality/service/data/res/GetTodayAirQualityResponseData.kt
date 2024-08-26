package com.ondosee.domain.airquality.service.data.res

import com.ondosee.domain.weather.service.data.enums.Element
import java.time.LocalTime

data class GetTodayAirQualityResponseData(
    val element: Element,
    val value: List<TimeZoneResponseData>
) {
    data class TimeZoneResponseData(
        val time: LocalTime,
        val value: Long
    )
}