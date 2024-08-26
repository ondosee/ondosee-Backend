package com.ondosee.thirdparty.openweather.data.web

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class GetTodayAirQualityOpenWeatherWebResponse @JsonCreator constructor(
    @JsonProperty("list") val list: List<GetTimeAirQualityWebResponse>
) {
    data class GetTimeAirQualityWebResponse @JsonCreator constructor(
        @JsonProperty("components") val components: Components,
        @JsonProperty("dt") val timestamp: String
    )

    data class Components @JsonCreator constructor(
        @JsonProperty("pm2_5") val pm25: Long,
        @JsonProperty("pm10") val pm10: Long
    )
}