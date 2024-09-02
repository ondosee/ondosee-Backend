package com.ondosee.domain.location.presentation.web.req

import org.springframework.web.bind.annotation.RequestParam

data class SearchLocationsByKeywordWebRequest(
    @RequestParam
    val keyword: String,

    @RequestParam(required = false, defaultValue = "1")
    val page: Int
)