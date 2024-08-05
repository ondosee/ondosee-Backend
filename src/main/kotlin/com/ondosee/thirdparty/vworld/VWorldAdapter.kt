package com.ondosee.thirdparty.vworld

import com.ondosee.common.spi.LocationPort
import com.ondosee.global.error.exception.ThirdPartyException
import com.ondosee.thirdparty.vworld.data.SearchDistrictResponseData
import com.ondosee.thirdparty.vworld.data.enums.ResponseStatus
import com.ondosee.thirdparty.vworld.properties.VWorldProperties
import org.springframework.context.annotation.Configuration

@Configuration
class VWorldAdapter(
    private val vWorldClient: VWorldClient,
    private val vWorldProperties: VWorldProperties
) : LocationPort {
    override fun searchDistrict(query: String): SearchDistrictResponseData =
        searchDistrict(query, 30)

    override fun searchDistrict(query: String, size: Int): SearchDistrictResponseData {
        val webResponse = vWorldClient.searchDistrict(
            query = query,
            size = size,
            type = "DISTRICT",
            category = "L4",
            request = "search",
            key = vWorldProperties.key
        )

        if(webResponse.status == ResponseStatus.ERROR){
            webResponse.error!!.run {
                throw ThirdPartyException(text, code.status)
            }
        }

        return webResponse.run {
            SearchDistrictResponseData(
                status = status,
                records = records!!,
                page = page!!,
                result = result!!
            )
        }
    }
}