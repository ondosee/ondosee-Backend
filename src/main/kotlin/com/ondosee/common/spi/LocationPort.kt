package com.ondosee.common.spi

import com.ondosee.domain.location.service.data.req.SearchDistrictsRequestData
import com.ondosee.domain.location.service.data.res.SearchDistrictsResponseData

interface LocationPort {
    fun searchDistricts(request: SearchDistrictsRequestData): SearchDistrictsResponseData
}