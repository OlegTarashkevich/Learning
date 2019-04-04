package com.prilaga.news.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.prilaga.news.R
import com.prilaga.news.data.network.model.Article
import com.prilaga.news.view.adapter.ArticleRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

class ArticlesFragment : BaseFragment(), INewsView<Article, Article.Param> {

    internal var adapter: ArticleRecyclerAdapter = ArticleRecyclerAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        page_recycler_view!!.layoutManager = LinearLayoutManager(context)
        page_recycler_view!!.adapter = adapter
//        articlePresenter!!.setView(this)
        // For testing
        //        onDataLoadEvent(Article.createParam());
        //        onDataLoadEvent(Article.createParam("the-next-web", RequestParam.SortBy.LATEST));
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
        adapter!!.clear()
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
//        adapter!!.setData(article.getArticles())
    }
    // endregion
}
