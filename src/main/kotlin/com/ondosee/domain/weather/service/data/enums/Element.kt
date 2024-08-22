package com.ondosee.domain.weather.service.data.enums

enum class Element(
    Description: String
) {
    PRECIPITATION_PROBABILITY("강수확률"),
    PRECIPITATION_TYPE("강수형태"),
    PRECIPITATION_IN_1_HOUR("1시간 강수량"),
    HUMIDITY("습도"),
    SNOW_IN_1_HOUR("1시간 신적설"),
    SKY("하늘 상태"),
    TEMPERATURE_FOR_1_HOUR("1시간 기온"),
    LOWEST_DAILY_TEMPERATURE("일 최저기온"),
    HIGHEST_DAILY_TEMPERATURE("일 최고기온"),
    EW_WIND_SPEED("풍속(동서성분)"),
    NS_WIND_SPEED("풍속(남북성분)"),
    PARTICULATE_MATTER_10("미세먼지"),
    PARTICULATE_MATTER_25("초미세먼지")
}