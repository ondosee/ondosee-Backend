package com.ondosee.thirdparty.vworld.data.web

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Item @JsonCreator constructor(
    @JsonProperty("title")
    val title: String,

    @JsonProperty("point")
    val point: Point
)