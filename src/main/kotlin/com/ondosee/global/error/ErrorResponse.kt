package com.ondosee.global.error

import com.ondosee.common.error.ErrorStatus
import com.ondosee.global.error.exception.OndoseeException
import com.ondosee.global.error.exception.ThirdPartyException
import org.springframework.validation.BindingResult
import org.springframework.web.servlet.NoHandlerFoundException

data class ErrorResponse(
    val message: String,
    val status: Int
) {
    companion object {
        fun of(e: OndoseeException) = ErrorResponse(
            message = e.message,
            status = e.status
        )

        fun of(e: ThirdPartyException) = ErrorResponse(
            message = e.message,
            status = e.status
        )

        fun of(e: BindingResult): ValidationErrorResponse {
            val fieldErrorMap = e.fieldErrors.associateBy({ it.field }, { it.defaultMessage })

            return ValidationErrorResponse(
                fieldError = fieldErrorMap,
                status = ErrorStatus.BAD_REQUEST
            )
        }

        fun of(e: NoHandlerFoundException) = NoHandlerErrorResponse(
            message = e.message.toString(),
            status = ErrorStatus.BAD_REQUEST
        )
    }
}
data class ValidationErrorResponse(
    val fieldError: Map<String, String?>,
    val status: Int
)

data class NoHandlerErrorResponse(
    val message: String,
    val status: Int
)