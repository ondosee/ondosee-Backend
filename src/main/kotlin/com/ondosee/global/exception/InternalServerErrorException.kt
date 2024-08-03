package com.ondosee.global.exception

import com.ondosee.common.error.ErrorStatus
import com.ondosee.global.error.exception.OndoseeException

class InternalServerErrorException(
    message: String,
    info: String
) : OndoseeException(
    message = message,
    info = info,
    status = ErrorStatus.INTERNAL_SERVER_ERROR
)