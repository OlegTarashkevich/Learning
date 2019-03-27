package com.prilaga.news.data.network

import com.prilaga.news.data.network.model.Article
import com.prilaga.news.data.network.model.Source
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

interface ApiService {

    @GET(V1 + ARTICLES)
    fun getArticles(@QueryMap params: Map<String, String>): Deferred<Article>

    @GET(V1 + SOURCES)
    fun getSources(@QueryMap params: Map<String, String>): Deferred<Source>

    companion object {
        const val V1 = "/v1"
        const val ARTICLES = "/articles"
        const val SOURCES = "/sources"
    }
}
