package com.prilaga.news.data

import com.prilaga.news.data.network.RemoteDataSource
import com.prilaga.news.data.network.model.Article
import kotlinx.coroutines.Deferred

/**
 * Created by Oleg Tarashkevich on 27/03/2019.
 */
class NewsRepository(val remoteDataSource: RemoteDataSource) : NewsDataSource {

    override fun getArticles(params: Map<String, String>): Deferred<Article> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}