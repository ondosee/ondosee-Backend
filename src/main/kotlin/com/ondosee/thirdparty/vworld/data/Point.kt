package com.ondosee.thirdparty.vworld.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Point @JsonCreator constructor(
    @JsonProperty("x")
    val x: String,

    @JsonProperty("y")
    val y: String
)
