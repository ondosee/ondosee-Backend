package com.ondosee.thirdparty.vworld

import com.ondosee.common.spi.LocationPort
import com.ondosee.global.error.exception.ThirdPartyException
import com.ondosee.thirdparty.vworld.data.enums.ResponseStatus
import com.ondosee.thirdparty.vworld.data.res.SearchDistrictResponseData
import com.ondosee.thirdparty.vworld.mapper.toResponse
import com.ondosee.thirdparty.vworld.properties.VWorldProperties
import org.springframework.context.annotation.Configuration

@Configuration
class VWorldAdapter(
    private val vWorldClient: VWorldClient,
    private val vWorldProperties: VWorldProperties
) : LocationPort {
    override fun searchDistricts(query: String, page: Int): SearchDistrictResponseData {

        val webResponse = vWorldClient.searchDistrict(
            query = query,
            size = 30,
            page = page,
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