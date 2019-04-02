package com.prilaga.news.data

import androidx.lifecycle.MutableLiveData
import com.prilaga.news.data.network.RemoteDataSource
import com.prilaga.news.data.network.model.Article
import com.prilaga.news.data.network.model.Source
import kotlinx.coroutines.Deferred

/**
 * Created by Oleg Tarashkevich on 27/03/2019.
 */
class NewsRepository(val remoteDataSource: RemoteDataSource) : ParamsObservable, NewsDataSource {

    // region ParamsObservable
    override val articleParam: MutableLiveData<Article.Param> = MutableLiveData()
    override var sourceParam: MutableLiveData<Source.Param> = object : MutableLiveData<Source.Param>() {
        override fun postValue(value: Source.Param) {
            super.postValue(value)
        }

        override fun setValue(value: Source.Param) {
            super.setValue(value)
        }
    }

    init {
        val param = Source.Param()
        sourceParam.value = param
    }
    // endregion

    // region NewsDataSource
    override fun getArticlesAsync(param: Article.Param): Deferred<Article> {
        return remoteDataSource.getArticlesAsync(param)
    }

    override fun getSourcesAsync(param: Source.Param): Deferred<Source> {
        return remoteDataSource.getSourcesAsync(param)
    }
    // endregion

}