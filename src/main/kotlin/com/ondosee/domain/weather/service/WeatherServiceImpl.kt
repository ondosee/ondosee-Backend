package com.ondosee.domain.weather.service

import com.ondosee.common.spi.airquality.AirQualityPort
import com.ondosee.common.spi.airquality.data.enums.AirElement
import com.ondosee.common.spi.airquality.data.req.GetTodayAirQualityRequestData
import com.ondosee.common.spi.airquality.data.res.GetTodayAirQualityResponseData
import com.ondosee.common.spi.weather.WeatherPort
import com.ondosee.common.spi.weather.data.enums.WeatherElement
import com.ondosee.common.spi.weather.data.req.GetTodayWeatherRequestData
import com.ondosee.common.spi.weather.data.res.GetTodayWeatherResponseData
import com.ondosee.domain.weather.presentation.web.enums.Significant
import com.ondosee.domain.weather.presentation.web.req.QueryTodayWeatherSignificantWebRequest
import com.ondosee.domain.weather.presentation.web.res.QueryTodayWeatherSignificantWebResponse
import org.springframework.stereotype.Service
import com.ondosee.domain.weather.presentation.web.res.QueryTodayWeatherSignificantWebResponse.SignificantResponseData as SignificantWebResponse
import com.ondosee.domain.weather.presentation.web.res.QueryTodayWeatherSignificantWebResponse.TimeZoneResponseData as TimeZoneWebData

@Service
class WeatherServiceImpl(
    private val weatherPort: WeatherPort,
    private val airQualityPort: AirQualityPort
) : WeatherService {
    override fun queryTodayWeatherSignificant(request: QueryTodayWeatherSignificantWebRequest): QueryTodayWeatherSignificantWebResponse {
        val weather = GetTodayWeatherRequestData(
            x = request.x,
            y = request.y
        ).run(weatherPort::getTodayWeather)

        val airQuality = GetTodayAirQualityRequestData(
            x = request.x,
            y = request.y
        ).run(airQualityPort::getTodayAirQuality)

        val weathers = getTodayWeather(weather) + getTodayAirQuality(airQuality)

        val response = QueryTodayWeatherSignificantWebResponse(
            weathers = weathers,
        )

        return response
    }

    private fun getTodayWeather(weather: List<GetTodayWeatherResponseData>): List<SignificantWebResponse> {
        val weatherResponse = mutableListOf<SignificantWebResponse>()

        weather.map { unit ->
            when(unit.weatherElement) {
                WeatherElement.TEMPERATURE_FOR_1_HOUR -> {
                    val maxTemp = unit.value.maxBy { it.value }.value
                    val minTemp = unit.value.minBy { it.value }.value

                    if(maxTemp >= 33L) unit.toResponse(Significant.HEAT_WAVE).run(weatherResponse::add)
                    if(minTemp <= 10L) unit.toResponse(Significant.COLD_WAVE).run(weatherResponse::add)
                }
                WeatherElement.HUMIDITY -> {
                    if(unit.value.any { it.value <= 35L }) unit.toResponse(Significant.DROUGHT).run(weatherResponse::add)
                }
                WeatherElement.WIND_SPEED -> {
                    if(unit.value.any { it.value >= 14L }) unit.toResponse(Significant.GALE).run(weatherResponse::add)
                }
                WeatherElement.PRECIPITATION_PROBABILITY -> {
                    if(unit.value.any { it.value >= 40L }) {
                        val sky = weather.find { it.weatherElement == WeatherElement.PRECIPITATION_TYPE }!!

                        val isRain = sky.value.any{ it.value == 1L || it.value == 2L || it.value == 4L }
                        val isSnow = sky.value.any{ it.value == 2L || it.value == 3L }

                        if(isRain) unit.toResponse(Significant.RAIN).run(weatherResponse::add)
                        if(isSnow) unit.toResponse(Significant.SNOW).run(weatherResponse::add)
                    }
                }
                else -> {}
            }
        }

        return weatherResponse
    }

    private fun getTodayAirQuality(airQuality: List<GetTodayAirQualityResponseData>): List<SignificantWebResponse> {
        val pm10 = airQuality.find { it.airElement == AirElement.PARTICULATE_MATTER_10 }!!
        val pm10Max = pm10.value.maxBy { it.value }.value
        val pm10Significant = when {
            pm10Max <= 15 -> Significant.BEST_10
            pm10Max <= 30 -> Significant.GOOD_10
            pm10Max <= 40 -> Significant.FAIR_10
            pm10Max <= 50 -> Significant.AVERAGE_10
            pm10Max <= 75 -> Significant.POOR_10
            pm10Max <= 100 -> Significant.BAD_10
            else -> Significant.WORST_10
        }
        val pm10Response = pm10.toResponse(pm10Significant)

        val pm25 = airQuality.find { it.airElement == AirElement.PARTICULATE_MATTER_25 }!!
        val pm25Max = pm25.value.maxBy { it.value }.value
        val pm25Significant =  when {
            pm25Max <= 7 -> Significant.BEST_25
            pm25Max <= 15 -> Significant.GOOD_25
            pm25Max <= 20 -> Significant.FAIR_25
            pm25Max <= 25 -> Significant.AVERAGE_25
            pm25Max <= 38 -> Significant.POOR_25
            pm25Max <= 50 -> Significant.BAD_25
            else -> Significant.WORST_25
        }
        val pm25Response = pm25.toResponse(pm25Significant)

        return listOf(pm10Response, pm25Response)
    }

    private fun GetTodayWeatherResponseData.toResponse(significant: Significant) =
        SignificantWebResponse(
            significant = significant,
            timeZone = value.map {
                TimeZoneWebData(
                    time = it.time,
                    value = "${it.value}"
                )
            }
        )

    private fun GetTodayAirQualityResponseData.toResponse(significant: Significant) =
        SignificantWebResponse(
            significant = significant,
            timeZone = value.map {
                TimeZoneWebData(
                    time = it.time,
                    value = "${it.value}"
                )
            }
        )
}