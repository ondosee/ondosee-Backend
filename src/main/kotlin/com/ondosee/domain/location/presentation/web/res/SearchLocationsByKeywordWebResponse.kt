package com.ondosee.domain.location.presentation.web.res

data class SearchLocationsByKeywordWebResponse(
    val page: Page,
    val locations: List<Location>
) {
    data class Page(
        val total: Int,
        val current: Int
    )

    data class Location(
        val title: String,
        val x: String,
        val y: String
    )
}