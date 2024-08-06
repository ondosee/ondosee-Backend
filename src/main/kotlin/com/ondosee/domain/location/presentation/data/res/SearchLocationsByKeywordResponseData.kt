package com.ondosee.domain.location.presentation.data.res

data class SearchLocationsByKeywordResponseData(
    val page: SearchPageResponseData,
    val locations: List<SearchLocationResponseData>
)