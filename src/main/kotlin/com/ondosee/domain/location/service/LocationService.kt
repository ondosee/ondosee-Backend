package com.ondosee.domain.location.service

import com.ondosee.domain.location.presentation.web.req.SearchLocationsByKeywordWebRequest
import com.ondosee.domain.location.presentation.web.res.SearchLocationsByKeywordWebResponse

interface LocationService {
    fun searchLocationsByKeyword(webRequest: SearchLocationsByKeywordWebRequest): SearchLocationsByKeywordWebResponse
}