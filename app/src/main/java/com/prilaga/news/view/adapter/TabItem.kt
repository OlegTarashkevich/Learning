package com.prilaga.news.view.adapter

import androidx.annotation.StringRes
import com.prilaga.news.R

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

enum class TabItem constructor(@param:StringRes val title: Int) {

    SOURCES(R.string.sources),
    ARTICLES(R.string.articles)
}
