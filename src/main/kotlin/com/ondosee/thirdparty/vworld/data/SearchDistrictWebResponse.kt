package com.ondosee.thirdparty.vworld.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer
import com.fasterxml.jackson.databind.ser.std.EnumSerializer
import com.ondosee.thirdparty.vworld.data.enums.ResponseStatus

data class SearchDistrictWebResponse @JsonCreator constructor(
    @JsonProperty("status")
    val status: ResponseStatus,

    @JsonProperty("records")
    val records: Record?,

    @JsonProperty("page")
    val page: Page?,

    @JsonProperty("result")
    val result: SearchDistrictResult?,

    @JsonProperty("error")
    val error: SearchDistrictError?
)
