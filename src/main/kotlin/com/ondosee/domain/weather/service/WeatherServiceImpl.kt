package com.ondosee.domain.weather.service

import com.ondosee.common.spi.AirQualityPort
import com.ondosee.common.spi.WeatherPort
import com.ondosee.domain.airquality.service.data.req.GetTodayAirQualityRequestData
import com.ondosee.domain.airquality.service.data.res.GetTodayAirQualityResponseData
import com.ondosee.domain.weather.presentation.data.enums.Significant
import com.ondosee.domain.weather.presentation.data.req.QueryTodayWeatherSignificantRequestData
import com.ondosee.domain.weather.presentation.data.res.QueryTodayWeatherSignificantResponseData
import com.ondosee.domain.weather.service.data.enums.Element
import com.ondosee.domain.weather.service.data.req.GetTodayWeatherRequestData
import com.ondosee.domain.weather.service.data.res.GetTodayWeatherResponseData
import org.springframework.stereotype.Service

@Service
class WeatherServiceImpl(
    private val weatherPort: WeatherPort,
    private val airQualityPort: AirQualityPort
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
                Element.TEMPERATURE_FOR_1_HOUR -> {
                    if(unit.value.any { it.value >= 33L })
                        Significant.HEAT_WAVE
                    else if(unit.value.any { it.value <= 10L})
                        Significant.COLD_WAVE
                    else null
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

        val pm10 = airQuality.find { it.element == Element.PARTICULATE_MATTER_10 }!!
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

        val pm25 = airQuality.find { it.element == Element.PARTICULATE_MATTER_25 }!!
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

    private fun GetTodayWeatherResponseData.toResponse(significant: Significant) =
        QueryTodayWeatherSignificantResponseData.SignificantResponseData(
            significant = significant,
            timeZone = value.map {
                QueryTodayWeatherSignificantResponseData.TimeZoneResponseData(
                    time = it.time,
                    value = "${it.value}"
                )
            }
        )

    private fun  GetTodayAirQualityResponseData.toResponse(significant: Significant) =
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