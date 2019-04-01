package com.prilaga.news.view.adapter;

import android.support.v7.widget.RecyclerView;
import com.sonejka.news.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public abstract class BaseRecyclerAdapter<D, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    private List<D> mData = new ArrayList<>();

    public void setData(List<D> data) {
        mData = ListUtil.isEmpty(data) ? new ArrayList<D>() : data;
        notifyDataSetChanged();
    }

    public void clear(){
        setData(null);
    }

    public D getItem(int position) {
        return ListUtil.getItem(mData, position);
    }

    protected abstract void onBindViewHolder(T holder, D item, int position);

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        onBindViewHolder(holder, getItem(position), position);
    }
}
