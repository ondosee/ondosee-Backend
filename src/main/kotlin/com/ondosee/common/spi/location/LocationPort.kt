package com.ondosee.common.spi.location

import com.ondosee.common.spi.location.data.req.SearchDistrictsRequestData

interface LocationPort {
    fun searchDistricts(request: SearchDistrictsRequestData): List<String>
}