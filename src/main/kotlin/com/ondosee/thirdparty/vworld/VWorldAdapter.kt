package com.ondosee.thirdparty.vworld

import com.ondosee.common.spi.LocationPort
import com.ondosee.domain.location.service.data.req.SearchDistrictsRequestData
import com.ondosee.global.error.exception.ThirdPartyException
import com.ondosee.thirdparty.vworld.client.VWorldClient
import com.ondosee.thirdparty.vworld.data.enums.ResponseStatus
import com.ondosee.domain.location.service.data.res.SearchDistrictsResponseData
import com.ondosee.thirdparty.vworld.data.mapper.toResponse
import com.ondosee.thirdparty.vworld.data.properties.VWorldProperties
import org.springframework.context.annotation.Configuration

@Configuration
class VWorldAdapter(
    private val vWorldClient: VWorldClient,
    private val vWorldProperties: VWorldProperties
) : LocationPort {

    override fun searchDistricts(request: SearchDistrictsRequestData): SearchDistrictsResponseData {

        val webResponse = vWorldClient.searchDistrict(
            query = request.query,
            size = 30,
            page = request.page,
            type = "DISTRICT",
            category = "L4",
            request = "search",
            key = vWorldProperties.key
        )

        if(webResponse.response.status == ResponseStatus.ERROR){
            webResponse.response.error!!.run {
                throw ThirdPartyException(text, code.status)
            }
        }

        return webResponse.toResponse()
    }
}