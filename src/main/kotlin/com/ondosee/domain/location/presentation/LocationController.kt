package com.ondosee.domain.location.presentation

import com.ondosee.domain.location.presentation.data.res.SearchLocationsByKeywordResponseData
import com.ondosee.domain.location.service.LocationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/location")
class LocationController(
    private val locationService: LocationService
) {
    @GetMapping("/coordinate")
    fun searchLocationsByKeyword(
        @RequestParam keyword: String,
        @RequestParam(required = false) page: Int = 1
    ): ResponseEntity<SearchLocationsByKeywordResponseData> =
        locationService.searchLocationsByKeyword(keyword, page)
            .run(ResponseEntity.status(HttpStatus.OK)::body)
}