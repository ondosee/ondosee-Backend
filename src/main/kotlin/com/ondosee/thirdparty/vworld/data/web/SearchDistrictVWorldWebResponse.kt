package com.ondosee.thirdparty.vworld.data.web

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.ondosee.thirdparty.vworld.data.enums.ResponseCode
import com.ondosee.thirdparty.vworld.data.enums.ResponseStatus

data class SearchDistrictVWorldWebResponse @JsonCreator constructor(
    @JsonProperty("response")
    val response: SearchDistrictWebResponse
) {
    data class SearchDistrictWebResponse @JsonCreator constructor(
        @JsonProperty("status")
        val status: ResponseStatus,

        @JsonProperty("page")
        val page: Page?,

        @JsonProperty("result")
        val result: SearchDistrictResultWebResponse?,

        @JsonProperty("error")
        val error: SearchDistrictErrorWebResponse?
    )

    data class Page @JsonCreator constructor(
        @JsonProperty("total")
        val total: Int,

        @JsonProperty("current")
        val current: Int,

        @JsonProperty("size")
        val size: Int
    )

    data class SearchDistrictErrorWebResponse @JsonCreator constructor(
        @JsonProperty("level")
        val level: Int,

        @JsonProperty("code")
        val code: ResponseCode,

        @JsonProperty("text")
        val text: String
    )

    data class SearchDistrictResultWebResponse @JsonCreator constructor(
        @JsonProperty("items")
        val items: List<Item>
    )

    data class Item @JsonCreator constructor(
        @JsonProperty("title")
        val title: String,

        @JsonProperty("point")
        val point: Point
    )

    data class Point @JsonCreator constructor(
        @JsonProperty("x")
        val x: Double,

        @JsonProperty("y")
        val y: Double
    )

}
