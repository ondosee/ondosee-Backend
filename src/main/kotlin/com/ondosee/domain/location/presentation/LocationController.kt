package com.ondosee.domain.location.presentation

import com.ondosee.domain.location.presentation.web.req.SearchLocationsByKeywordWebRequest
import com.ondosee.domain.location.presentation.web.res.SearchLocationsByKeywordWebResponse
import com.ondosee.domain.location.service.LocationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/location")
class LocationController(
    private val locationService: LocationService
) {
    @GetMapping("/coordinate")
    fun searchLocationsByKeyword(webRequest: SearchLocationsByKeywordWebRequest): ResponseEntity<SearchLocationsByKeywordWebResponse> =
        locationService.searchLocationsByKeyword(webRequest)
            .run(ResponseEntity.status(HttpStatus.OK)::body)
}