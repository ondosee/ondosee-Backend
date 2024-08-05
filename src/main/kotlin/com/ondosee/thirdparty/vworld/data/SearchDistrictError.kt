package com.ondosee.thirdparty.vworld.data

import com.ondosee.thirdparty.vworld.data.enums.ResponseCode

data class SearchDistrictError(
    val level: Int,
    val code: ResponseCode,
    val text: String
)