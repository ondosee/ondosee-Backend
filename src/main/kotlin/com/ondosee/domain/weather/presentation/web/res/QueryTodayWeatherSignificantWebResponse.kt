package com.ondosee.domain.weather.presentation.web.res

import com.ondosee.domain.weather.presentation.web.enums.Significant
import java.time.LocalTime

data class QueryTodayWeatherSignificantWebResponse(
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
