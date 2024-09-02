package com.ondosee.thirdparty.openweather

import com.ondosee.common.spi.airquality.AirQualityPort
import com.ondosee.common.spi.airquality.data.req.GetTodayAirQualityRequestData
import com.ondosee.common.spi.airquality.data.res.GetTodayAirQualityResponseData
import com.ondosee.thirdparty.openweather.client.OpenWeatherClient
import com.ondosee.thirdparty.openweather.data.properties.OpenWeatherProperties
import com.ondosee.thirdparty.openweather.mapper.toResponse
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

@Component
class OpenWeatherAdapter(
    private val openWeatherClient: OpenWeatherClient,
    private val openWeatherProperties: OpenWeatherProperties
) : AirQualityPort {
    override fun getTodayAirQuality(request: GetTodayAirQualityRequestData): List<GetTodayAirQualityResponseData> {
        val start = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0))
            .run { atZone(ZoneId.systemDefault()).toInstant() }.epochSecond

        val end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0))
            .run { atZone(ZoneId.systemDefault()).toInstant() }.epochSecond

        val webResponse = openWeatherClient.getTodayAirQuality(
            lon = request.x,
            lat = request.y,
            start = start,
            end = end,
            appid = openWeatherProperties.key
        )

        return webResponse.toResponse()
    }
}