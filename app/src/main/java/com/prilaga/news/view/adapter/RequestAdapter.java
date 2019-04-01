package com.prilaga.news.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.sonejka.news.R;

/**
 * Created by Oleg Tarashkevich on 02.04.17.
 */

public class RequestAdapter extends BaseAdapter implements Filterable {

    private String[] mArray;

    public RequestAdapter(String[] items) {
        mArray = items;
    }

    @Override
    public int getCount() {
        return mArray.length;
    }

    @Override
    public String getItem(int index) {
        return mArray[index];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_enum, parent, false);
        }
        TextView fieldTextView = (TextView) convertView;

        String item = getItem(position);
        fieldTextView.setText(item);

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            }
        };
    }
}