package com.prilaga.news.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.prilaga.news.R;
import com.prilaga.news.data.network.model.Source;
import com.prilaga.news.view.adapter.SourceRecyclerAdapter;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class SourcesFragment extends BaseFragment implements INewsView<Source, Source.Param> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject SourcePresenter sourcePresenter;
    @Inject
    SourceRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, rootView);
        getFragmentComponent().inject(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        sourcePresenter.setView(this);
        // For testing
//        onDataLoadEvent(Source.param());
//        onDataLoadEvent(Source.param(RequestParam.Category.BUSINESS, RequestParam.Language.EN, RequestParam.Country.US));
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        sourcePresenter.unSubscribe();
    }

    // region INewsView
    @Subscribe
    @Override
    public void onDataLoadEvent(Source.Param param) {
        adapter.clear();
        sourcePresenter.loadData(param);
    }

    @Override
    public void onStartLoading() {
        showProgress(true);
    }

    @Override
    public void onFailure(String message) {
        showProgress(false);
    }

    @Override
    public void updateRecycleView(Source source) {
        showProgress(false);
        adapter.setData(source.getSources());
    }
    // endregion
}
