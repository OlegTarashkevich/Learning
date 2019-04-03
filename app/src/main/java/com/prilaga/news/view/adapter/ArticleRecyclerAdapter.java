package com.prilaga.news.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.prilaga.news.R;
import com.prilaga.news.data.network.model.Article;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class ArticleRecyclerAdapter extends BaseRecyclerAdapter<Article.Entry, ArticleRecyclerAdapter.ArticleViewHolder> {

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleRecyclerAdapter.ArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false));
    }

    @Override
    protected void onBindViewHolder(ArticleViewHolder holder, Article.Entry entry, int position) {
//        holder.cardView.setSource(entry);
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.article_card_view) ArticleCardView cardView;

        public ArticleViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
        }
    }
}