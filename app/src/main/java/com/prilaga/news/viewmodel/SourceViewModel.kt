package com.prilaga.news.viewmodel

import androidx.lifecycle.MutableLiveData
import com.prilaga.news.data.NewsRepository
import com.prilaga.news.data.network.model.RequestParam
import com.prilaga.news.data.network.model.Source
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

/**
 * Created by Oleg Tarashkevich on 28/03/2019.
 */
class SourceViewModel(val repository: NewsRepository) : BaseViewModel() {

    val sourceData = MutableLiveData<Source>()

    fun loadNews() {
        doWorkIO {
            try {

                val param = Source.param(
                    RequestParam.Category.BUSINESS,
                    RequestParam.Language.EN,
                    RequestParam.Country.US
                )
                val source = repository.getSources(param).await()
                doWorkInMainThread { sourceData.value = source }
//                throw RuntimeException("test error") // for testing

            } catch (e: Throwable) {
                doWorkInMainThread { errorData.callWithValue(e) }
            }
        }

    }

    override fun onDestroyView() {
        cancelJob()
    }
}