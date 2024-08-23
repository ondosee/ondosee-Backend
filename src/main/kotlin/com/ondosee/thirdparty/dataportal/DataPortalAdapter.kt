package com.ondosee.thirdparty.dataportal

import com.ondosee.common.spi.WeatherPort
import com.ondosee.domain.weather.service.data.req.GetTodayWeatherRequestData
import com.ondosee.domain.weather.service.data.res.GetTodayWeatherResponseData
import com.ondosee.global.error.exception.ThirdPartyException
import com.ondosee.thirdparty.dataportal.client.DataPortalClient
import com.ondosee.thirdparty.dataportal.data.mapper.toResponse
import com.ondosee.thirdparty.dataportal.data.properties.DataPorterProperties
import org.springframework.stereotype.Component
import java.time.LocalDate
import kotlin.math.*

@Component
class DataPortalAdapter(
    val dataPortalProperties: DataPorterProperties,
    val dataPortalClient: DataPortalClient
) : WeatherPort {

    companion object {
        const val XO = 43
        const val YO = 136

        const val DEGRAD = Math.PI / 180.0

        const val RE = 6371.00877 / 5.0
        const val SLAT1 = 30.0 * DEGRAD
        const val SLAT2 = 60.0 * DEGRAD
        const val OLON = 126.0 * DEGRAD
        const val OLAT = 38.0 * DEGRAD
    }

    override fun getTodayWeather(request: GetTodayWeatherRequestData): List<GetTodayWeatherResponseData> {
        val (nx, ny) = toNXY(
            request.x.toDouble(),
            request.y.toDouble()
        )

        val baseDate = LocalDate.now().run { "${String.format("%2d", monthValue)}${String.format("%2d", dayOfMonth)}" }

        val webResponse = dataPortalClient.getTodayWeathers(
            nx = nx,
            ny = ny,
            serviceKey = dataPortalProperties.key,
            dataType = "JSON",
            base_date = baseDate,
            base_time = "2300",
            numOfRows = 290,
            pageNo = 1
        )

        val header = webResponse.response.header

        if(header.resultCode != "00")
            throw ThirdPartyException(header.resultMsg.name, header.resultMsg.status)

        return webResponse.toResponse()
    }

    private fun toNXY(x: Double, y: Double): List<Int> {
        val sn = (tan(Math.PI * 0.25 + SLAT2 * 0.5) / tan(Math.PI * 0.25 + SLAT1 * 0.5))
            .let { ln(cos(SLAT1) / cos(SLAT2)) / ln(it) }

        val sf = tan(Math.PI * 0.25 + SLAT1 * 0.5)
            .run { pow(sn) * cos(SLAT1) / sn }

        val ro = tan(Math.PI * 0.25 + OLAT * 0.5)
            .run { RE * sf / pow(sn) }

        val ra = tan(Math.PI * 0.25 + x * DEGRAD * 0.5)
            .run { RE * sf / pow(sn) }

        var theta = y * DEGRAD - OLON
        if (theta > Math.PI) theta -= 2.0 * Math.PI
        if (theta < -Math.PI) theta += 2.0 * Math.PI
        theta *= sn

        val nx = floor(ra * sin(theta) + XO + 0.5).toInt()
        val ny = floor(ro - ra * cos(theta) + YO + 0.5).toInt()

        return listOf(nx, ny)
    }
}