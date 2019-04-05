package com.prilaga.news.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.prilaga.data.utils.Logger
import com.prilaga.news.R
import com.prilaga.news.data.network.model.Article
import com.prilaga.news.view.adapter.ArticleRecyclerAdapter
import com.prilaga.news.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

class ArticlesFragment : BaseFragment(), INewsView<Article, Article.Param> {

    private val articleViewModel by viewModel<ArticleViewModel>()
    internal var adapter: ArticleRecyclerAdapter = ArticleRecyclerAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        page_recycler_view!!.layoutManager = LinearLayoutManager(context)
        page_recycler_view!!.adapter = adapter
        // For testing
        //        onDataLoadEvent(Article.createParam());
        //        onDataLoadEvent(Article.createParam("the-next-web", RequestParam.SortBy.LATEST));

        lifecycle.addObserver(articleViewModel)   // Receive the source data
        articleViewModel.articleData.observe(this, Observer {
            Logger.separator()
            updateRecycleView(it)
        })

        // Receive the error
        articleViewModel.errorData.observe(this, Observer {
            it.printStackTrace()
//            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
//        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
//        EventBus.getDefault().unregister(this)
//        articlePresenter!!.unSubscribe()
    }

    // region INewsView
//    @Subscribe
    override fun onDataLoadEvent(param: Article.Param) {
        adapter.clear()
//        articlePresenter!!.loadData(createParam)
    }

    override fun onStartLoading() {
        showProgress(true)
    }

    override fun onFailure(message: String) {
        showProgress(false)
    }

//    @Subscribe
    override fun updateRecycleView(article: Article) {
        showProgress(false)
        adapter.setData(article.articles)
    }
    // endregion
}
