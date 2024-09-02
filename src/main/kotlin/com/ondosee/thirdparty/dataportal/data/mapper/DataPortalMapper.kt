package com.ondosee.thirdparty.dataportal.data.mapper

import com.ondosee.common.spi.weather.data.enums.Element
import com.ondosee.common.spi.weather.data.res.GetTodayWeatherResponseData
import com.ondosee.common.spi.weather.data.res.GetTodayWeatherResponseData.TimeZoneResponseData
import com.ondosee.thirdparty.dataportal.data.enums.Category
import com.ondosee.thirdparty.dataportal.data.web.GetTodayWeatherDataPorterWebResponse
import java.time.LocalTime
import java.time.format.DateTimeFormatter.ofPattern

fun GetTodayWeatherDataPorterWebResponse.toResponse(): List<com.ondosee.common.spi.weather.data.res.GetTodayWeatherResponseData> =
    response.body!!
        .itemList.item
        .groupBy { it.category }
        .mapNotNull { unit ->
            when(unit.key) {
                Category.POP -> com.ondosee.common.spi.weather.data.enums.Element.PRECIPITATION_PROBABILITY
                Category.PTY -> com.ondosee.common.spi.weather.data.enums.Element.PRECIPITATION_TYPE
                Category.PCP -> com.ondosee.common.spi.weather.data.enums.Element.PRECIPITATION_IN_1_HOUR
                Category.REH -> com.ondosee.common.spi.weather.data.enums.Element.HUMIDITY
                Category.SNO -> com.ondosee.common.spi.weather.data.enums.Element.SNOW_IN_1_HOUR
                Category.SKY -> com.ondosee.common.spi.weather.data.enums.Element.SKY
                Category.TMP -> com.ondosee.common.spi.weather.data.enums.Element.TEMPERATURE_FOR_1_HOUR
                Category.TMN -> com.ondosee.common.spi.weather.data.enums.Element.LOWEST_DAILY_TEMPERATURE
                Category.TMX -> com.ondosee.common.spi.weather.data.enums.Element.HIGHEST_DAILY_TEMPERATURE
                Category.WSD -> com.ondosee.common.spi.weather.data.enums.Element.WIND_SPEED
                else -> null
            }?.let { element ->
                com.ondosee.common.spi.weather.data.res.GetTodayWeatherResponseData(
                    element = element,
                    value = unit.value.map { value ->
                        val timeZoneValue = when (element) {
                            com.ondosee.common.spi.weather.data.enums.Element.PRECIPITATION_IN_1_HOUR -> {
                                when (value.fcstValue) {
                                    "강수없음" -> "0.0"
                                    "1mm 미만" -> "0.1"
                                    "30.0mm~50.0mm" -> "30.0"
                                    "50.0mm 이상" -> "50.0"
                                    else -> value.fcstValue.substring(0, value.fcstValue.length - 2)
                                }
                            }

                            com.ondosee.common.spi.weather.data.enums.Element.SNOW_IN_1_HOUR -> {
                                when (value.fcstValue) {
                                    "적설없음" -> "0.0"
                                    "1.0mm 미만" -> "0.1"
                                    "5.0mm 이상" -> "5.0"
                                    else -> value.fcstValue.substring(0, value.fcstValue.length - 2)
                                }
                            }

                            else -> value.fcstValue
                        }.toDouble().toLong()

                        TimeZoneResponseData(
                            time = LocalTime.parse(value.fcstTime, ofPattern("HHmm")),
                            value = timeZoneValue
                        )
                    }.sortedBy { it.time }
                )
            }
        }