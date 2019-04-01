package com.prilaga.news.view.fragment;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public interface INewsView<N, E> {

    void onDataLoadEvent(E event);

    void onStartLoading();

    void onFailure(String message);

    void updateRecycleView(N news);
}
