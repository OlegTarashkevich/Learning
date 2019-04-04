package com.prilaga.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.prilaga.news.data.NewsRepository
import com.prilaga.news.data.network.model.Source
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Oleg Tarashkevich on 28/03/2019.
 */
class SourceViewModel(private val repository: NewsRepository) : BaseViewModel() {

    val sourceData = MutableLiveData<Source>()

    private val paramObserver = Observer<Source.Param> { loadSources(it) }

    /**
     * Listen for changes of Param of the Source
     */
    override fun onCreateView() {
        repository.sourceParam.observeForever(paramObserver)
    }

    override fun onDestroyView() {
        repository.sourceParam.removeObserver(paramObserver)
        super.onDestroyView()
    }

    private fun loadSources(param: Source.Param) {
        doWorkIO {
            try {
                val source = repository.getSourcesAsync(param).await()
                withContext(Dispatchers.Main) { sourceData.value = source }
            } catch (e: Throwable) {
                onError(e)
            }
        }
    }

}