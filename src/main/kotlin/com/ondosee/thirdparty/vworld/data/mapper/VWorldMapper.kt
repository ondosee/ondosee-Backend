package com.ondosee.thirdparty.vworld.data.mapper


import com.ondosee.domain.location.service.data.res.SearchDistrictResponseData
import com.ondosee.domain.location.service.data.res.SearchDistrictResponseData.*
import com.ondosee.thirdparty.vworld.data.web.Page
import com.ondosee.thirdparty.vworld.data.web.Point
import com.ondosee.thirdparty.vworld.data.web.SearchDistrictResultWebResponse
import com.ondosee.thirdparty.vworld.data.web.SearchDistrictVWoldWebResponse

fun SearchDistrictVWoldWebResponse.toResponse() = SearchDistrictResponseData(
    status = response.status,
    page = response.page!!.toResponse(),
    result = response.result?.toResponse() ?: listOf()
)

fun SearchDistrictResultWebResponse.toResponse() =
    items.map {
        ResultResponseData(
            title = it.title,
            point = it.point.toResponse()
        )
    }

fun Page.toResponse() = PageResponseData(
    total = total,
    current = current,
    size = size
)

fun Point.toResponse() = PointResponseData(
    x = x,
    y = y
)