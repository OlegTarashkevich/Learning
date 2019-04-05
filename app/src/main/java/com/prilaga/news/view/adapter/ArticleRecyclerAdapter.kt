package com.prilaga.news.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prilaga.news.R
import com.prilaga.news.data.network.model.Article
import com.prilaga.news.view.widget.ArticleCardView

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

class ArticleRecyclerAdapter : BaseRecyclerAdapter<Article.Entry, ArticleRecyclerAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleRecyclerAdapter.ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, entry: Article.Entry, position: Int) {
        holder.cardView.setSource(entry);
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: ArticleCardView = itemView.findViewById(R.id.article_card_view)
    }
}