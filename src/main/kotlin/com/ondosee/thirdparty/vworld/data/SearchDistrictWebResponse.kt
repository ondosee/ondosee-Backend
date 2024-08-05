package com.ondosee.thirdparty.vworld.data

import com.ondosee.thirdparty.vworld.data.enums.ResponseStatus

data class SearchDistrictWebResponse(
    val status: ResponseStatus,
    val records: Record?,
    val page: Page?,
    val result: SearchDistrictResult?,
    val error: SearchDistrictError?
)
