package com.ondosee.thirdparty.dataportal.data.mapper

import com.ondosee.domain.weather.service.data.enums.Element
import com.ondosee.domain.weather.service.data.res.GetTodayWeatherResponseData
import com.ondosee.domain.weather.service.data.res.GetTodayWeatherResponseData.TimeZoneResponseData
import com.ondosee.thirdparty.dataportal.data.enums.Category
import com.ondosee.thirdparty.dataportal.data.web.GetTodayWeatherDataPorterWebResponse
import java.time.LocalTime
import java.time.format.DateTimeFormatter.ofPattern

fun GetTodayWeatherDataPorterWebResponse.toResponse(): List<GetTodayWeatherResponseData> =
    response.body!!
        .itemList.item
        .groupBy { it.category }
        .mapNotNull { unit ->
            when(unit.key) {
                Category.POP -> Element.PRECIPITATION_PROBABILITY
                Category.PTY -> Element.PRECIPITATION_TYPE
                Category.PCP -> Element.PRECIPITATION_IN_1_HOUR
                Category.REH -> Element.HUMIDITY
                Category.SNO -> Element.SNOW_IN_1_HOUR
                Category.SKY -> Element.SKY
                Category.TMP -> Element.TEMPERATURE_FOR_1_HOUR
                Category.TMN -> Element.LOWEST_DAILY_TEMPERATURE
                Category.TMX -> Element.HIGHEST_DAILY_TEMPERATURE
                Category.WSD -> Element.WIND_SPEED
                else -> null
            }?.let { element ->
                GetTodayWeatherResponseData(
                    element = element,
                    value = unit.value.map { value ->
                        TimeZoneResponseData(
                            time = LocalTime.parse(value.fcstTime, ofPattern("HHmm")),
                            value = value.fcstValue.toLong()
                        )
                    }.sortedBy { it.time }
                )
            }
        }