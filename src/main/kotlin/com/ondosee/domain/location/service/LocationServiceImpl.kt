package com.ondosee.domain.location.service

import com.ondosee.common.spi.LocationPort
import com.ondosee.domain.location.presentation.web.req.SearchLocationsByKeywordWebRequest
import com.ondosee.domain.location.presentation.web.res.SearchLocationsByKeywordWebResponse
import com.ondosee.domain.location.presentation.web.res.SearchLocationsByKeywordWebResponse.Location
import com.ondosee.domain.location.presentation.web.res.SearchLocationsByKeywordWebResponse.Page
import com.ondosee.domain.location.service.data.req.SearchDistrictsRequestData
import org.springframework.stereotype.Service

@Service
class LocationServiceImpl(
    private val locationPort: LocationPort
) : LocationService {
    override fun searchLocationsByKeyword(webRequest: SearchLocationsByKeywordWebRequest): SearchLocationsByKeywordWebResponse {
        val districts = locationPort.searchDistricts(
            SearchDistrictsRequestData(
                query = webRequest.keyword,
                page = webRequest.page
            )
        )

        val pageData = districts.page.run {
            Page(
                total = total,
                current = current
            )
        }

        val locations = districts.result.map { location ->
            Location(
                title = location.title,
                x = location.point.x,
                y = location.point.y
            )
        }

        return SearchLocationsByKeywordWebResponse(
            page = pageData,
            locations = locations
        )
    }
}