package com.ondosee.thirdparty.dataportal.data.web

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.ondosee.thirdparty.dataportal.data.enums.Category
import com.ondosee.thirdparty.dataportal.data.enums.ResultMsg

data class GetTodayWeatherDataPorterWebResponse @JsonCreator constructor(
    @JsonProperty("response") val response: GetTodayWeather
) {
    data class GetTodayWeather @JsonCreator constructor(
        @JsonProperty("header") val header: Header,
        @JsonProperty("Body") val body: Body
    )

    data class Header @JsonCreator constructor(
        @JsonProperty("resultCode") val resultCode: String,
        @JsonProperty("resultMsg") val resultMsg: ResultMsg
    )

    data class Body @JsonCreator constructor(
        @JsonProperty("dataType") val dataType: String,
        @JsonProperty("pageNo") val pageNo: Int,
        @JsonProperty("numOfRows") val numOfRows: Int,
        @JsonProperty val totalCount: Int,
        @JsonProperty("items") val itemList: ItemList
    )

    data class ItemList @JsonCreator constructor(
        @JsonProperty("item") val item: List<Item>
    )

    data class Item @JsonCreator constructor(
        @JsonProperty("baseDate") val baseDate: String,
        @JsonProperty("baseTime") val baseTime: String,
        @JsonProperty("category") val category: Category,
        @JsonProperty("fcstDate") val fcstDate: String,
        @JsonProperty("fcstTime") val fcstTime: String,
        @JsonProperty("fcstValue") val fcstValue: String,
        @JsonProperty("nx") val nx: Int,
        @JsonProperty("ny") val ny: Int
    )
}

