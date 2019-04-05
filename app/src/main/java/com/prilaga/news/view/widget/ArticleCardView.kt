package com.prilaga.news.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.prilaga.news.R
import com.prilaga.news.data.network.model.Article
import com.prilaga.news.util.TextUtil
import kotlinx.android.synthetic.main.cardview_article.view.*

/**
 * Created by Oleg Tarashkevich on 02.04.17.
 */

class ArticleCardView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    CardView(context, attrs, defStyleAttr) {

    private var mEntry: Article.Entry? = null

    init {
        inflate(context, R.layout.cardview_article, this)
        article_share_button.setOnClickListener {
            mEntry?.let {
                val message = resources.getString(R.string.app_name) + ":\n" +
                        it.title + "\n\n" +
                        it.description + "\n" +
                        it.url
//                ShareUtil.shareText(message, R.string.app_name)
            }
        }
    }

    fun setSource(entry: Article.Entry) {
        mEntry = entry
        if (mEntry == null) {
            visibility = View.INVISIBLE
        } else {
            visibility = View.VISIBLE

            mEntry?.let {
                article_name_text_view.text = it.info
                article_title_text_view.text = it.title
                article_url_text_view.text = it.url
                article_description_text_view.text = it.description

                Glide.with(context)
                    .load(it.urlToImage)
                    .into(article_logo_view)
            }

        }
    }

}
