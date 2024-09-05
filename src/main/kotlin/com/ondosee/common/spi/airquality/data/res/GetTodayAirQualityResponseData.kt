package com.ondosee.common.spi.airquality.data.res

import com.ondosee.common.spi.airquality.data.enums.AirElement

import java.time.LocalTime

data class GetTodayAirQualityResponseData(
    val airElement: AirElement,
    val value: List<TimeZoneResponseData>
) {
    data class TimeZoneResponseData(
        val time: LocalTime,
        val value: Long
    )
}