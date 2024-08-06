package com.ondosee.thirdparty.vworld.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Record @JsonCreator constructor(
    @JsonProperty("total")
    val total: Int,

    @JsonProperty("current")
    val current: Int
)
