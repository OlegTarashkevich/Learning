package com.prilaga.news.data.network

import androidx.annotation.StringDef

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

enum class API private constructor(private val baseUrl: String, private val name_: String) {

    PRODUCTION("https://newsapi.org", "api_news"),
    MOCK("https://mock.com", "api_mock");

    val isMock: Boolean
        get() = this == MOCK

    @StringDef(Query.SOURCE, Query.SORT_BY, Query.API_KEY, Query.CATEGORY, Query.LANGUAGE, Query.COUNTRY)
    @Retention(RetentionPolicy.SOURCE)
    annotation class Query {
        companion object {
            const val SOURCE = "source"
            const val SORT_BY = "sortBy"
            const val API_KEY = "apiKey"
            const val CATEGORY = "category"
            const val LANGUAGE = "language"
            const val COUNTRY = "country"
        }
    }

    companion object {
        val API_KEY = "4c96c741e1444309ad86faafd9774f87"
        val TAG_KEY = "API"
    }

}
