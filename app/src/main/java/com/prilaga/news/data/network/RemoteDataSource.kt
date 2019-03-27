package com.prilaga.news.data.network

import com.prilaga.news.data.NewsDataSource
import com.prilaga.news.data.network.model.Article
import com.prilaga.news.data.network.model.Source
import kotlinx.coroutines.Deferred
import java.util.*

/**
 * Created by Oleg Tarashkevich on 27/03/2019.
 */
class RemoteDataSource(val api: ApiService) : NewsDataSource {

    override fun getArticles(param: Article.Param): Deferred<Article> {
        val params = HashMap<String, String>()
        param.source?.let { params[API.Query.SOURCE] = it }
        param.sortBy?.let { params[API.Query.SORT_BY] = it }
        return api.getArticles(params)
    }

    override fun getSources(param: Source.Param): Deferred<Source> {
        val params = HashMap<String, String>()
        param.category?.let { params[API.Query.CATEGORY] = it }
        param.language?.let { params[API.Query.LANGUAGE] = it }
        param.country?.let { params[API.Query.COUNTRY] = it }
        return api.getSources(params)
    }
}