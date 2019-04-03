package com.prilaga.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.prilaga.news.data.NewsRepository
import com.prilaga.news.data.ParamsObservable
import com.prilaga.news.data.network.model.RequestParam
import com.prilaga.news.data.network.model.Source

/**
 * Created by Oleg Tarashkevich on 28/03/2019.
 */
class SourceViewModel(private val repository: NewsRepository) : BaseViewModel(), ParamsObservable by repository {

    val sourceData = MutableLiveData<Source>()
    private val paramObserver = Observer<Source.Param> { loadNews(it) }

    override fun onCreateView() {
        // Listen for changes of Param of the Source
        repository.sourceParam.observeForever(paramObserver)
    }

    override fun onDestroyView() {
        repository.sourceParam.removeObserver(paramObserver)
        super.onDestroyView()
    }

    fun loadNews(
        @RequestParam.Category category: String?,
        @RequestParam.Language language: String?,
        @RequestParam.Country country: String?
    ) {
        val param: Source.Param = Source.Param.param(category, language, country)
        loadNews(param)
    }

    fun loadNews(param: Source.Param) {
        doWorkIO {
            try {
                val source = repository.getSourcesAsync(param).await()
                doWorkInMainThread { sourceData.value = source }
//                throw RuntimeException("test error") // for testing

            } catch (e: Throwable) {
                onError(e)
            }
        }
    }

}