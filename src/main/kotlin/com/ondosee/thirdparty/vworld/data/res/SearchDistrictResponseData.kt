package com.ondosee.thirdparty.vworld.data.res

import com.ondosee.thirdparty.vworld.data.enums.ResponseStatus

data class SearchDistrictResponseData (
    val status: ResponseStatus,
    val record: RecordResponseData,
    val page: PageResponseData,
    val result: SearchDistrictResultResponseData
)
