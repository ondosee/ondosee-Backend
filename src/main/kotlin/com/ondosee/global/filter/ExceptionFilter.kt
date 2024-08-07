package com.ondosee.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.ondosee.global.error.exception.OndoseeException
import com.ondosee.global.error.exception.ThirdPartyException
import com.ondosee.global.error.ErrorResponse
import com.ondosee.common.error.ErrorStatus
import com.ondosee.global.exception.InternalServerErrorException
import com.ondosee.common.logger.LoggerDelegator
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionFilter : OncePerRequestFilter() {

    private val log by LoggerDelegator()

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        runCatching {
            filterChain.doFilter(request, response)
        }.onFailure { e ->
            when (e) {
                is OndoseeException -> {
                    log.error("Ondosee Exception :: message = {}, info = {}, status = {}", e.message, e.info, e.status)
                    sendError(response, ErrorResponse.of(e))
                }
                is ThirdPartyException -> {
                    log.error("ThirdParty Exception :: message = {}, status = {}", e.message, e.status)
                    sendError(response, ErrorResponse.of(e))

                }
                else -> {
                    log.error("Internal Exception :: message = {}, Status = {}", e.message, ErrorStatus.INTERNAL_SERVER_ERROR)
                    sendError(response, ErrorResponse.of(InternalServerErrorException("서버 에러", e.message.toString())))
                }
            }
        }
    }

    private fun sendError(response: HttpServletResponse, errorResponse: ErrorResponse) {
        response.status = errorResponse.status
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = StandardCharsets.UTF_8.name()

        ObjectMapper().writeValueAsString(errorResponse).run(response.writer::write)
    }
}