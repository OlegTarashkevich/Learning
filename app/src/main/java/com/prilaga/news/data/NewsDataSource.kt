package com.prilaga.news.data

import com.prilaga.news.data.network.model.Article
import com.prilaga.news.data.network.model.Source
import kotlinx.coroutines.Deferred


/**
 * Created by Oleg Tarashkevich on 27/03/2019.
 */
interface NewsDataSource {

    fun getArticles(param: Article.Param): Deferred<Article>

    fun getSources(param: Source.Param): Deferred<Source>
}