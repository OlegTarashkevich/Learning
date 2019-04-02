package com.prilaga.news.viewmodel

import com.prilaga.news.data.NewsRepository
import com.prilaga.news.data.network.model.Source

/**
 * Created by Oleg Tarashkevich on 02/04/2019.
 */
class RequestViewModel(val repository: NewsRepository) : BaseViewModel() {

    fun startRequestParam(category: String, language: String, country: String){
        repository.sourceParam.value = Source.Param(category, language, country)
    }

}