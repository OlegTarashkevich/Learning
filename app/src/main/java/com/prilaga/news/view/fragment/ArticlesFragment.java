//package com.prilaga.news.view.fragment;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import com.sonejka.news.R;
//import com.sonejka.news.mvp.model.Article;
//import com.sonejka.news.mvp.presenter.news.ArticlePresenter;
//import com.sonejka.news.mvp.view.adapter.ArticleRecyclerAdapter;
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//
//import javax.inject.Inject;
//
///**
// * Created by Oleg Tarashkevich on 01.04.17.
// */
//
//public class ArticlesFragment extends BaseFragment implements INewsView<Article, Article.Param> {
//
//    @BindView(R.id.recycler_view) RecyclerView recyclerView;
//
//    @Inject ArticlePresenter articlePresenter;
//    @Inject ArticleRecyclerAdapter adapter;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
//        ButterKnife.bind(this, rootView);
//        getFragmentComponent().inject(this);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adapter);
//
//        return rootView;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        articlePresenter.setView(this);
//        // For testing
////        onDataLoadEvent(Article.param());
////        onDataLoadEvent(Article.param("the-next-web", RequestParam.SortBy.LATEST));
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        EventBus.getDefault().unregister(this);
//        articlePresenter.unSubscribe();
//    }
//
//    // region INewsView
//    @Subscribe
//    @Override
//    public void onDataLoadEvent(Article.Param param) {
//        adapter.clear();
//        articlePresenter.loadData(param);
//    }
//
//    @Override
//    public void onStartLoading() {
//        showProgress(true);
//    }
//
//    @Override
//    public void onFailure(String message) {
//        showProgress(false);
//    }
//
//    @Subscribe
//    @Override
//    public void updateRecycleView(Article article) {
//        showProgress(false);
//        adapter.setData(article.getArticles());
//    }
//    // endregion
//}
