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
                        val timeZoneValue = when(element) {
                            Element.PRECIPITATION_IN_1_HOUR -> {
                                when(value.fcstValue){
                                    "강수없음" -> "0.0"
                                    "1mm 미만" -> "0.1"
                                    "30.0mm~50.0mm" -> "30.0"
                                    "50.0mm 이상" -> "50.0"
                                    else -> value.fcstValue.substring(0, value.fcstValue.length - 2)
                                }
                            }
                            Element.SNOW_IN_1_HOUR -> {
                                when(value.fcstValue){
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