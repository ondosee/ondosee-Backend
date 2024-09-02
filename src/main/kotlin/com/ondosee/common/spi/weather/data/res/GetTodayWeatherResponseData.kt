package com.ondosee.common.spi.weather.data.res

import com.ondosee.common.spi.weather.data.enums.Element
import java.time.LocalTime

data class GetTodayWeatherResponseData(
    val element: Element,
    val value: List<TimeZoneResponseData>
) {
    data class TimeZoneResponseData(
        val time: LocalTime,
        val value: Long
    )
}