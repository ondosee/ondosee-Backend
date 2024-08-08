package com.ondosee.common.cache

object CacheKeyUtil {
    fun generateCacheKey(query: String, page: Int): String {
        return "$query-$page"
    }
}