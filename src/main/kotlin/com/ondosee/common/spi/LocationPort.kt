package com.ondosee.common.spi

import com.ondosee.thirdparty.vworld.data.SearchDistrictResponseData

interface LocationPort {
    fun searchDistrict(query: String, page: Int): SearchDistrictResponseData
}