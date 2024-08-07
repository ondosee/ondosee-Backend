package com.ondosee.thirdparty.vworld.data.web

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class SearchDistrictVWoldWebResponse @JsonCreator constructor(
    @JsonProperty("response")
    val response: SearchDistrictWebResponse
)
