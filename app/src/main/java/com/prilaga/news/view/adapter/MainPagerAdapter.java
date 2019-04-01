package com.prilaga.news.view.adapter;

/**
 * Created by Oleg Tarashkevich on 12.11.15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.sonejka.news.mvp.view.fragment.ArticlesFragment;
import com.sonejka.news.mvp.view.fragment.BaseFragment;
import com.sonejka.news.mvp.view.fragment.SourcesFragment;
import com.sonejka.news.util.TextUtil;

import javax.inject.Inject;
import java.util.WeakHashMap;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private TabItem[] mItems = TabItem.values();
    private WeakHashMap<Integer, BaseFragment> mFragments = new WeakHashMap<>();

    @Inject
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        TabItem item = mItems[position];
        BaseFragment baseFragment = null;

        switch (item) {
            case SOURCES:
                baseFragment = new SourcesFragment();
                break;
            case ARTICLES:
                baseFragment = new ArticlesFragment();
                break;
        }

        mFragments.put(position, baseFragment);
        return baseFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TextUtil.string(mItems[position].getTitle());
    }

    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public float getPageWidth(int position) {
        return .9f;
    }

    public WeakHashMap<Integer, BaseFragment> getFragments() {
        return mFragments;
    }

}