package com.prilaga.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.prilaga.news.data.NewsRepository
import com.prilaga.news.data.network.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Oleg Tarashkevich on 04/04/2019.
 */
class ArticleViewModel(private val repository: NewsRepository) : BaseViewModel() {

    val articleData = MutableLiveData<Article>()

    private val paramObserver = Observer<Article.Param> { loadArticles(it) }

    /**
     * Listen for changes of Param of the Article
     */
    override fun onCreateView() {
        repository.articleParam.observeForever(paramObserver)
    }

    override fun onDestroyView() {
        repository.articleParam.removeObserver(paramObserver)
        super.onDestroyView()
    }

    private fun loadArticles(param: Article.Param) {
        doWorkIO {
            try {
                val source = repository.getArticlesAsync(param).await()
                withContext(Dispatchers.Main) { articleData.value = source }
            } catch (e: Throwable) {
                onError(e)
            }
        }
    }
}