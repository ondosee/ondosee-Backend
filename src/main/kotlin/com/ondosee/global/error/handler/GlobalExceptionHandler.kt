package com.ondosee.global.error.handler

import com.ondosee.global.error.ErrorResponse
import com.ondosee.global.error.NoHandlerErrorResponse
import com.ondosee.global.error.ValidationErrorResponse
import com.ondosee.global.error.exception.OndoseeException
import com.ondosee.global.error.exception.ThirdPartyException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(OndoseeException::class)
    fun ondoseeExceptionHandler(e: OndoseeException): ResponseEntity<ErrorResponse> =
        ResponseEntity(ErrorResponse.of(e), HttpStatus.valueOf(e.status))

    @ExceptionHandler(ThirdPartyException::class)
    fun thirdPartyExceptionHandler(e: ThirdPartyException): ResponseEntity<ErrorResponse> =
        ResponseEntity(ErrorResponse.of(e), HttpStatus.valueOf(e.status))

    @ExceptionHandler(NoHandlerFoundException::class)
    fun noHandlerFoundException(e: NoHandlerFoundException): ResponseEntity<NoHandlerErrorResponse> =
        ResponseEntity(ErrorResponse.of(e), HttpStatus.NOT_FOUND)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ValidationErrorResponse> =
        ResponseEntity(ErrorResponse.of(e), HttpStatus.BAD_REQUEST)
}