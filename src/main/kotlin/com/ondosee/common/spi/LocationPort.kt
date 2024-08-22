package com.ondosee.common.spi

import com.ondosee.domain.location.service.data.res.SearchDistrictResponseData

interface LocationPort {
    fun searchDistricts(query: String, page: Int): SearchDistrictResponseData
}