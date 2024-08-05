package com.ondosee.thirdparty.vworld.data.enums

enum class ResponseCode(
    val status: Int
) {
    PARAM_REQUIRED(400),
    INVALID_TYPE(400),
    INVALID_RANGE(400),
    INVALID_KEY(401),
    INCORRECT_KEY(401),
    UNAVAILABLE_KEY(409),
    OVER_REQUEST_LIMIT(429),
    SYSTEM_ERROR(500),
    UNKNOWN_ERROR(500)
}