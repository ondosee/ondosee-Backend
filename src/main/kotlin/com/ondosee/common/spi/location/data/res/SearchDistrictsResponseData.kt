package com.ondosee.common.spi.location.data.res

import com.ondosee.thirdparty.vworld.data.enums.ResponseStatus

data class SearchDistrictsResponseData(
    val status: ResponseStatus,
    val page: Page,
    val results: List<Result>
) {
    data class Page(
        val total: Int,
        val current: Int,
        val size: Int
    )

    data class Result(
        val title: String,
        val point: Point
    )

    data class Point(
        val x: Double,
        val y: Double
    )
}