package com.prilaga.news.viewmodel

import com.prilaga.news.data.NewsRepository
import com.prilaga.news.data.network.model.Article
import com.prilaga.news.data.network.model.RequestParam
import com.prilaga.news.data.network.model.Source

/**
 * Created by Oleg Tarashkevich on 02/04/2019.
 */
class RequestViewModel(private val repository: NewsRepository) : BaseViewModel() {

    fun sourceParam() = repository.sourceParam

    fun createSourceRequest(
        @RequestParam.Category category: String?,
        @RequestParam.Language language: String?,
        @RequestParam.Country country: String?
    ) {
        val param: Source.Param = Source.createParam(category, language, country)
        repository.sourceParam.value = param
    }

    fun createArticleRequest(source: String, @RequestParam.SortBy sortBy: String) {
        val param: Article.Param = Article.createParam(source, sortBy)
        repository.articleParam.value = param
    }
}