package com.prilaga.news.data

import androidx.lifecycle.MutableLiveData
import com.prilaga.news.data.network.model.Article
import com.prilaga.news.data.network.model.Source
import kotlinx.coroutines.Deferred

/**
 * Created by Oleg Tarashkevich on 27/03/2019.
 */
interface NewsDataSource {
    fun getArticlesAsync(param: Article.Param): Deferred<Article>
    fun getSourcesAsync(param: Source.Param): Deferred<Source>
}

interface ParamsObservable {
    val sourceParam: MutableLiveData<Source.Param>
    val articleParam: MutableLiveData<Article.Param>
}