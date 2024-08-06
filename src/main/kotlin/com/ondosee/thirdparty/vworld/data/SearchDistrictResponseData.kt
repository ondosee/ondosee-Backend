package com.ondosee.thirdparty.vworld.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.ondosee.thirdparty.vworld.data.enums.ResponseStatus

data class SearchDistrictResponseData @JsonCreator constructor(
    @JsonProperty("status")
    val status: ResponseStatus,

    @JsonProperty("records")
    val records: Record,

    @JsonProperty("page")
    val page: Page,

    @JsonProperty("result")
    val result: SearchDistrictResult
)
