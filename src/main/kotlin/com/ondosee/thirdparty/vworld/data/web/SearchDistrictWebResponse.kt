package com.ondosee.thirdparty.vworld.data.web

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.ondosee.thirdparty.vworld.data.enums.ResponseStatus


data class SearchDistrictWebResponse @JsonCreator constructor(
    @JsonProperty("status")
    val status: ResponseStatus,

    @JsonProperty("record")
    val record: Record?,

    @JsonProperty("page")
    val page: Page?,

    @JsonProperty("result")
    val result: SearchDistrictResultWebResponse?,

    @JsonProperty("error")
    val error: SearchDistrictErrorWebResponse?
)
