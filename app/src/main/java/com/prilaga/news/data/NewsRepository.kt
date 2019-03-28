package com.prilaga.news.data

import com.prilaga.news.data.network.RemoteDataSource
import com.prilaga.news.data.network.model.Article
import com.prilaga.news.data.network.model.Source
import kotlinx.coroutines.Deferred

/**
 * Created by Oleg Tarashkevich on 27/03/2019.
 */
class NewsRepository(val remoteDataSource: RemoteDataSource) : NewsDataSource {

    override fun getArticles(param: Article.Param): Deferred<Article> {
        return remoteDataSource.getArticles(param)
    }

    override fun getSources(param: Source.Param): Deferred<Source> {
        return remoteDataSource.getSources(param)
    }

}