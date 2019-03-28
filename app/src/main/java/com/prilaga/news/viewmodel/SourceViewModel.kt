package com.prilaga.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prilaga.news.data.NewsRepository
import com.prilaga.news.data.network.model.Source

/**
 * Created by Oleg Tarashkevich on 28/03/2019.
 */
class SourceViewModel(val repository: NewsRepository) : ViewModel() {

    val data = MutableLiveData<Source>()

    fun loadNews(){

    }
    
}