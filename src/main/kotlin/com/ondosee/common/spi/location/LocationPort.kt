package com.ondosee.common.spi.location

import com.ondosee.common.spi.location.data.req.SearchDistrictsRequestData
import com.ondosee.common.spi.location.data.res.SearchDistrictsResponseData

interface LocationPort {
    fun searchDistricts(request: SearchDistrictsRequestData): SearchDistrictsResponseData
}