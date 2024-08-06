package com.ondosee.domain.location.service

import com.ondosee.common.spi.LocationPort
import com.ondosee.domain.location.presentation.data.res.SearchLocationResponseData
import com.ondosee.domain.location.presentation.data.res.SearchLocationsByKeywordResponseData
import com.ondosee.domain.location.presentation.data.res.SearchPageResponseData
import org.springframework.stereotype.Service

@Service
class LocationServiceImpl(
    private val locationPort: LocationPort
) : LocationService {
    override fun searchLocationsByKeyword(keyword: String, page: Int): SearchLocationsByKeywordResponseData {
        val districts = locationPort.searchDistricts(keyword, page)

        val pageData = districts.page.run {
            SearchPageResponseData(
                total = total,
                current = current
            )
        }

        val locations = districts.result.items.map { location ->
            SearchLocationResponseData(
                title = location.title,
                x = location.point.x,
                y = location.point.y
            )
        }

        return SearchLocationsByKeywordResponseData(
            page = pageData,
            locations = locations
        )
    }
}