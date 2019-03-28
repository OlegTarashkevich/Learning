package com.prilaga.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prilaga.news.data.NewsRepository
import com.prilaga.news.data.network.model.RequestParam
import com.prilaga.news.data.network.model.Source
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

/**
 * Created by Oleg Tarashkevich on 28/03/2019.
 */
class SourceViewModel(val repository: NewsRepository) : ViewModel() {

    val sourceData = MutableLiveData<Source>()

    val viewModelJob = Job()
    val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun loadNews() {
        viewModelScope.launch {

            val source = withContext (IO) {
                val param = Source.param(
                    RequestParam.Category.BUSINESS,
                    RequestParam.Language.EN,
                    RequestParam.Country.US
                )
                 repository.getSources(param).await()
            }

            sourceData.value = source
        }
    }

    fun cancelJob() {
        viewModelJob.cancel()
    }

}