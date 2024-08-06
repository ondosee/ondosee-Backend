package com.ondosee.common.spi

import com.ondosee.thirdparty.vworld.data.SearchDistrictResponseData

interface LocationPort {
    fun searchDistricts(query: String, page: Int): SearchDistrictResponseData
}