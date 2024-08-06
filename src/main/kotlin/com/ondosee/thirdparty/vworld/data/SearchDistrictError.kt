package com.ondosee.thirdparty.vworld.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.ondosee.thirdparty.vworld.data.enums.ResponseCode

data class SearchDistrictError @JsonCreator constructor(
    @JsonProperty("level")
    val level: Int,

    @JsonProperty("code")
    val code: ResponseCode,

    @JsonProperty("text")
    val text: String
)