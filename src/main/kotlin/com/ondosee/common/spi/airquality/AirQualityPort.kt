package com.ondosee.common.spi.airquality

import com.ondosee.common.spi.airquality.data.req.GetTodayAirQualityRequestData
import com.ondosee.common.spi.airquality.data.res.GetTodayAirQualityResponseData

interface AirQualityPort {
    fun getTodayAirQuality(request: GetTodayAirQualityRequestData): List<GetTodayAirQualityResponseData>
}