package com.ondosee.thirdparty.dataportal.data.enums

enum class ResultMsg(
    val status: Int
) {
    UNKNOWN_ERROR(500),
    APPLICATION_ERROR(500),
    DB_ERROR(500),
    NODATA_ERROR(404),
    HTTP_ERROR(404),
    SERVICETIME_OUT(408),
    INVALID_REQUEST_PARAMETER_ERROR(400),
    NO_MANDATORY_REQUEST_PARAMETERS_ERROR(400),
    NO_OPENAPI_SERVICE_ERROR(404),
    SERVICE_ACCESS_DENIED_ERROR(401),
    TEMPORARILY_DISABLE_THE_SERVICEKEY_ERROR(401),
    LIMITED_NUMBER_OF_SERVICE_REQUESTS_EXCEEDS_ERROR(429),
    SERVICE_KEY_IS_NOT_REGISTERED_ERROR(401),
    DEADLINE_HAS_EXPIRED_ERROR(401),
    UNREGISTERED_IP_ERROR(401),
    UNSIGNED_CALL_ERROR(401)

}