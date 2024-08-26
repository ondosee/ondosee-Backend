package com.ondosee.thirdparty.openweather

import com.ondosee.common.spi.AirQualityPort
import com.ondosee.domain.airquality.service.data.req.GetTodayAirQualityRequestData
import com.ondosee.domain.airquality.service.data.res.GetTodayAirQualityResponseData
import com.ondosee.thirdparty.openweather.client.OpenWeatherClient
import com.ondosee.thirdparty.openweather.data.properties.OpenWeatherProperties
import com.ondosee.thirdparty.openweather.mapper.toResponse
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.LocalDateTime

@Component
class OpenWeatherAdapter(
    private val openWeatherClient: OpenWeatherClient,
    private val openWeatherProperties: OpenWeatherProperties
) : AirQualityPort {
    override fun getTodayAirQuality(request: GetTodayAirQualityRequestData): List<GetTodayAirQualityResponseData> {
        val startDay = LocalDateTime.now().minusHours(9).toLocalDate()
            .run { Timestamp.valueOf(atStartOfDay()).time.toString().substring(0 ..< 10) }

        val endDay = LocalDateTime.now().plusDays(1).minusHours(8).toLocalDate()
            .run { Timestamp.valueOf(atStartOfDay()).time.toString().substring(0 ..< 10) }

        val webResponse = openWeatherClient.getTodayAirQuality(
            lon = request.x,
            lat = request.y,
            start = startDay,
            end = endDay,
            appid = openWeatherProperties.key
        )

        return webResponse.toResponse()
    }
}