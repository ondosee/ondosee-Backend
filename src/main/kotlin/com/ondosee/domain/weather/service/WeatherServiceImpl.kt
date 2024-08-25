package com.ondosee.domain.weather.service

import com.ondosee.common.spi.WeatherPort
import com.ondosee.domain.weather.presentation.data.enums.Significant
import com.ondosee.domain.weather.presentation.data.req.QueryTodayWeatherSignificantRequestData
import com.ondosee.domain.weather.presentation.data.res.QueryTodayWeatherSignificantResponseData
import com.ondosee.domain.weather.presentation.data.res.QueryTodayWeatherSignificantResponseData.SignificantResponseData
import com.ondosee.domain.weather.presentation.data.res.QueryTodayWeatherSignificantResponseData.TimeZoneResponseData
import com.ondosee.domain.weather.service.data.enums.Element
import com.ondosee.domain.weather.service.data.req.GetTodayWeatherRequestData
import com.ondosee.domain.weather.service.data.res.GetTodayWeatherResponseData
import org.springframework.stereotype.Service

@Service
class WeatherServiceImpl(
    private val weatherPort: WeatherPort
) : WeatherService {
    override fun queryTodayWeatherSignificant(request: QueryTodayWeatherSignificantRequestData): QueryTodayWeatherSignificantResponseData {
        val weather = GetTodayWeatherRequestData(
            x = request.x,
            y = request.y,
            location = request.location
        ).run(weatherPort::getTodayWeather)

        var precipitationProbability: List<GetTodayWeatherResponseData.TimeZoneResponseData>? = null
        var sky: List<GetTodayWeatherResponseData.TimeZoneResponseData>? = null

        val significant = weather.mapNotNull { unit ->
            when (unit.element) {
                Element.HIGHEST_DAILY_TEMPERATURE -> {
                    takeIf { unit.value.any { it.value >= 33L } }
                        ?.let { Significant.HEAT_WAVE }
                }
                Element.LOWEST_DAILY_TEMPERATURE -> {
                    takeIf { unit.value.any { it.value <= 10L } }
                        ?.let { Significant.COLD_WAVE }
                }
                Element.HUMIDITY -> {
                    takeIf { unit.value.any { it.value >= 35L } }
                        ?.let { Significant.DROUGHT }
                }
                Element.WIND_SPEED -> {
                    takeIf { unit.value.any { it.value >= 14L } }
                        ?.let { Significant.GALE }
                }
                Element.PRECIPITATION_PROBABILITY -> {
                    precipitationProbability = unit.value
                    null
                }
                Element.SKY -> {
                    sky = unit.value
                    null
                }
                else -> null
            }?.let { unit.toResponse(it) }
        }.toMutableList()

        if(precipitationProbability?.any { it.value >= 65L } == true) {
            if(sky?.any { it.value == 1L || it.value == 2L || it.value == 4L } == true)
                SignificantResponseData(
                    significant = Significant.RAIN,
                    timeZone = precipitationProbability!!.toResponse()
                ).run(significant::add)
            if(sky?.any { it.value == 2L || it.value == 3L } == true)
                SignificantResponseData(
                    significant = Significant.SNOW,
                    timeZone = precipitationProbability!!.toResponse()
                ).run(significant::add)
        }

        val response = QueryTodayWeatherSignificantResponseData(
            weathers = significant,
        )

        return response
    }

    private fun GetTodayWeatherResponseData.toResponse(significant: Significant) =
        SignificantResponseData(
            significant = significant,
            timeZone = value.toResponse()
        )

    private fun List<GetTodayWeatherResponseData.TimeZoneResponseData>.toResponse() =
        map {
            TimeZoneResponseData(
                time = it.time,
                value = "${it.value}"
            )
        }
}