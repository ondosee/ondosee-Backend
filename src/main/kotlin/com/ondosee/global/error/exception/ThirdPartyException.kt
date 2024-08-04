package com.ondosee.global.error.exception

open class ThirdPartyException(
    override val message: String,
    val status: Int
) : RuntimeException()