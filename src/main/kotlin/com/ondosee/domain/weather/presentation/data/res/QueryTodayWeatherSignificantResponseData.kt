package com.ondosee.domain.weather.presentation.data.res

import com.ondosee.domain.weather.presentation.data.enums.Significant
import java.time.LocalTime

data class QueryTodayWeatherSignificantResponseData(
    val weathers: List<SignificantResponseData>
) {
    data class SignificantResponseData(
        val significant: Significant,
        val timeZone: List<TimeZoneResponseData>
    )

    data class TimeZoneResponseData(
        val time: LocalTime,
        val value: String
    )
}
