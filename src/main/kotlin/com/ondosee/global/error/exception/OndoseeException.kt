package com.ondosee.global.error.exception

open class OndoseeException(
    override val message: String,
    val info: String,
    val status: Int
) : RuntimeException()