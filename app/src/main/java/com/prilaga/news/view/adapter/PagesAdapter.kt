package com.prilaga.news.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prilaga.news.R

/**
 * Created by Oleg Tarashkevich on 03/04/2019.
 */
class PagesAdapter : RecyclerView.Adapter<PagesAdapter.PageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return PageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_list, parent, false))
    }

    override fun getItemCount(): Int = 2

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {

    }

    class PageViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }
}