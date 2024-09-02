package com.ondosee.common.spi.location.data.res

import com.ondosee.thirdparty.vworld.data.enums.ResponseStatus

data class SearchDistrictsResponseData(
    val status: ResponseStatus,
    val page: PageResponseData,
    val result: List<ResultResponseData>
) {
    data class ResultResponseData(
        val title: String,
        val point: PointResponseData
    )

    data class PointResponseData(
        val x: String,
        val y: String
    )

    data class PageResponseData(
        val total: Int,
        val current: Int,
        val size: Int
    )
}
