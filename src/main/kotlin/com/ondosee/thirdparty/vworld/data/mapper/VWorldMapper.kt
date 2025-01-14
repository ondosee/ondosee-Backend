package com.ondosee.thirdparty.vworld.data.mapper

import com.ondosee.common.spi.location.data.res.SearchDistrictsResponseData
import com.ondosee.thirdparty.vworld.data.web.SearchDistrictVWorldWebResponse
import com.ondosee.common.spi.location.data.res.SearchDistrictsResponseData.Page as PageResponseData
import com.ondosee.common.spi.location.data.res.SearchDistrictsResponseData.Point as PointResponseData
import com.ondosee.common.spi.location.data.res.SearchDistrictsResponseData.Result as ResultResponseData
import com.ondosee.thirdparty.vworld.data.web.SearchDistrictVWorldWebResponse.Page as PageWebData
import com.ondosee.thirdparty.vworld.data.web.SearchDistrictVWorldWebResponse.Point as PointWebData
import com.ondosee.thirdparty.vworld.data.web.SearchDistrictVWorldWebResponse.SearchDistrictResultWebResponse as ResultWebData

fun SearchDistrictVWorldWebResponse.toResponse() = SearchDistrictsResponseData(
    status = response.status,
    page = response.page!!.toResponse(),
    results = response.result?.toResponse() ?: listOf()
)

fun ResultWebData.toResponse() =
    items.map {
        ResultResponseData(
            title = it.title,
            point = it.point.toResponse()
        )
    }

fun PageWebData.toResponse() = PageResponseData(
    total = total,
    current = current,
    size = size
)

fun PointWebData.toResponse() = PointResponseData(
    x = x,
    y = y
)