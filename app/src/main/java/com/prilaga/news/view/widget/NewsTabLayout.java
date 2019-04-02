package com.prilaga.news.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.tabs.TabLayout;

/**
 * Created by Oleg Tarashkevich on 09.08.16.
 */
public class NewsTabLayout extends TabLayout {

     private CustomTabLayoutListener mCustomTabLayoutListener;

    public NewsTabLayout(Context context) {
        super(context);
    }

    public NewsTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface CustomTabLayoutListener {
        void onPageSelected(int position);
    }
}
