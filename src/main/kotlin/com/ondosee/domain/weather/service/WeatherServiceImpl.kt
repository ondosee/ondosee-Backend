package com.ondosee.domain.weather.service

import com.ondosee.common.spi.airquality.AirQualityPort
import com.ondosee.common.spi.airquality.data.enums.AirElement
import com.ondosee.common.spi.airquality.data.req.GetTodayAirQualityRequestData
import com.ondosee.common.spi.airquality.data.res.GetTodayAirQualityResponseData
import com.ondosee.common.spi.weather.WeatherPort
import com.ondosee.common.spi.weather.data.enums.WeatherElement
import com.ondosee.domain.weather.presentation.data.enums.Significant
import com.ondosee.domain.weather.presentation.data.req.QueryTodayWeatherSignificantRequestData
import com.ondosee.domain.weather.presentation.data.res.QueryTodayWeatherSignificantResponseData
import org.springframework.stereotype.Service

@Service
class WeatherServiceImpl(
    private val weatherPort: WeatherPort,
    private val airQualityPort: AirQualityPort
) : WeatherService {
    override fun queryTodayWeatherSignificant(request: QueryTodayWeatherSignificantRequestData): QueryTodayWeatherSignificantResponseData {
        val weather = com.ondosee.common.spi.weather.data.req.GetTodayWeatherRequestData(
            x = request.x,
            y = request.y
        ).run(weatherPort::getTodayWeather)

        var precipitationProbability: List<com.ondosee.common.spi.weather.data.res.GetTodayWeatherResponseData.TimeZoneResponseData>? = null
        var sky: List<com.ondosee.common.spi.weather.data.res.GetTodayWeatherResponseData.TimeZoneResponseData>? = null

        val significant = weather.mapNotNull { unit ->
            when (unit.weatherElement) {
                WeatherElement.TEMPERATURE_FOR_1_HOUR -> {
                    if(unit.value.any { it.value >= 33L })
                        Significant.HEAT_WAVE
                    else if(unit.value.any { it.value <= 10L})
                        Significant.COLD_WAVE
                    else null
                }
                WeatherElement.HUMIDITY -> {
                    takeIf { unit.value.any { it.value >= 35L } }
                        ?.let { Significant.DROUGHT }
                }
                WeatherElement.WIND_SPEED -> {
                    takeIf { unit.value.any { it.value >= 14L } }
                        ?.let { Significant.GALE }
                }
                WeatherElement.PRECIPITATION_PROBABILITY -> {
                    precipitationProbability = unit.value
                    null
                }
                WeatherElement.SKY -> {
                    sky = unit.value
                    null
                }
                else -> null
            }?.let { unit.toResponse(it) }
        }.toMutableList()

        if(precipitationProbability?.any { it.value >= 40L } == true) {
            if(sky?.any { it.value == 1L || it.value == 2L || it.value == 4L } == true)
                QueryTodayWeatherSignificantResponseData.SignificantResponseData(
                    significant = Significant.RAIN,
                    timeZone = precipitationProbability!!.map {
                        QueryTodayWeatherSignificantResponseData.TimeZoneResponseData(
                            time = it.time,
                            value = "${it.value}"
                        )
                    }
                ).run(significant::add)
            if(sky?.any { it.value == 2L || it.value == 3L } == true)
                QueryTodayWeatherSignificantResponseData.SignificantResponseData(
                    significant = Significant.SNOW,
                    timeZone = precipitationProbability!!.map {
                        QueryTodayWeatherSignificantResponseData.TimeZoneResponseData(
                            time = it.time,
                            value = "${it.value}"
                        )
                    }
                ).run(significant::add)
        }

        val airQuality = GetTodayAirQualityRequestData(
            x = request.x,
            y = request.y
        ).run(airQualityPort::getTodayAirQuality)

        val pm10 = airQuality.find { it.airElement == AirElement.PARTICULATE_MATTER_10 }!!
        val pm10Max = pm10.value.maxBy { it.value }.value

        when{
            pm10Max <= 15 -> Significant.BEST_10
            pm10Max <= 30 -> Significant.GOOD_10
            pm10Max <= 40 -> Significant.FAIR_10
            pm10Max <= 50 -> Significant.AVERAGE_10
            pm10Max <= 75 -> Significant.POOR_10
            pm10Max <= 100 -> Significant.BAD_10
            else -> Significant.WORST_10
        }.let { pm10.toResponse(it) }
         .run(significant::add)

        val pm25 = airQuality.find { it.airElement == AirElement.PARTICULATE_MATTER_25 }!!
        val pm25Max = pm25.value.maxBy { it.value }.value

        when{
            pm25Max <= 7 -> Significant.BEST_25
            pm25Max <= 15 -> Significant.GOOD_25
            pm25Max <= 20 -> Significant.FAIR_25
            pm25Max <= 25 -> Significant.AVERAGE_25
            pm25Max <= 38 -> Significant.POOR_25
            pm25Max <= 50 -> Significant.BAD_25
            else -> Significant.WORST_25
        }.let { pm25.toResponse(it) }
         .run(significant::add)

        val response = QueryTodayWeatherSignificantResponseData(
            weathers = significant,
        )

        return response
    }

    private fun com.ondosee.common.spi.weather.data.res.GetTodayWeatherResponseData.toResponse(significant: Significant) =
        QueryTodayWeatherSignificantResponseData.SignificantResponseData(
            significant = significant,
            timeZone = value.map {
                QueryTodayWeatherSignificantResponseData.TimeZoneResponseData(
                    time = it.time,
                    value = "${it.value}"
                )
            }
        )

    private fun GetTodayAirQualityResponseData.toResponse(significant: Significant) =
        QueryTodayWeatherSignificantResponseData.SignificantResponseData(
            significant = significant,
            timeZone = value.map {
                QueryTodayWeatherSignificantResponseData.TimeZoneResponseData(
                    time = it.time,
                    value = "${it.value}"
                )
            }
        )
}