package com.prilaga.news.view.adapter;

import android.support.annotation.StringRes;
import com.sonejka.news.R;
import lombok.Getter;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public enum TabItem {

     SOURCES(R.string.sources),
    ARTICLES(R.string.articles);

    @Getter private int title;

    TabItem(@StringRes int title) {
        this.title = title;
    }
}
