package com.ondosee.domain.location.service

import com.ondosee.domain.location.presentation.data.res.SearchLocationsByKeywordResponseData

interface LocationService {
    fun searchLocationsByKeyword(keyword: String, page: Int): SearchLocationsByKeywordResponseData
}