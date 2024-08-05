package com.ondosee.thirdparty.vworld.data

import com.ondosee.thirdparty.vworld.data.enums.ResponseStatus

data class SearchDistrictResponseData(
    val status: ResponseStatus,
    val records: Record,
    val page: Page,
    val result: SearchDistrictResult
)
