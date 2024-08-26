package com.ondosee.thirdparty.openweather

import com.ondosee.common.spi.AirQualityPort
import com.ondosee.domain.airquality.service.data.req.GetTodayAirQualityRequestData
import com.ondosee.domain.airquality.service.data.res.GetTodayAirQualityResponseData
import com.ondosee.thirdparty.openweather.client.OpenWeatherClient
import com.ondosee.thirdparty.openweather.data.properties.OpenWeatherProperties
import com.ondosee.thirdparty.openweather.mapper.toResponse
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.LocalDate

@Component
class OpenWeatherAdapter(
    private val openWeatherClient: OpenWeatherClient,
    private val openWeatherProperties: OpenWeatherProperties
) : AirQualityPort {
    override fun getTodayAirQuality(request: GetTodayAirQualityRequestData): List<GetTodayAirQualityResponseData> {
        val today = LocalDate.now()

        val webResponse = openWeatherClient.getTodayAirQuality(
            lon = request.x,
            lat = request.y,
            start = Timestamp.valueOf(today.atStartOfDay()).time.toString(),
            end = Timestamp.valueOf(today.plusDays(1).atStartOfDay()).time.toString(),
            appid = openWeatherProperties.key
        )

        return webResponse.toResponse()
    }
}