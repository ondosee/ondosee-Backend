package com.ondosee.thirdparty.vworld.data.web

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class SearchDistrictResultWebResponse @JsonCreator constructor(
    @JsonProperty("type")
    val type: String,

    @JsonProperty("items")
    val items: List<Item>
)