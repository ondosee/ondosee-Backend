package com.ondosee.thirdparty.vworld.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class SearchDistrictResult @JsonCreator constructor(
    @JsonProperty("items")
    val items: List<Item>,
)