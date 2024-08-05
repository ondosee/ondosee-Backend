package com.ondosee.common.spi

import com.ondosee.thirdparty.vworld.data.SearchDistrictResponseData

interface LocationPort {
    fun searchDistrict(query: String): SearchDistrictResponseData
    fun searchDistrict(query: String, size: Int): SearchDistrictResponseData
}