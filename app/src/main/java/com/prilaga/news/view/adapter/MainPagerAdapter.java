package com.prilaga.news.view.adapter;

/**
 * Created by Oleg Tarashkevich on 12.11.15.
 */

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.prilaga.news.view.fragment.ArticlesFragment;
import com.prilaga.news.view.fragment.BaseFragment;
import com.prilaga.news.view.fragment.SourcesFragment;

import java.util.WeakHashMap;

public class MainPagerAdapter extends FragmentStateAdapter {

    private WeakHashMap<Integer, BaseFragment> mFragments = new WeakHashMap<>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment baseFragment = null;

        switch (position) {
            case 0:
                baseFragment = new SourcesFragment();
                break;
            case 1:
                baseFragment = new ArticlesFragment();
                break;
        }

        mFragments.put(position, baseFragment);
        return baseFragment;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return TextUtil.string(mItems[position].getTitle());
//    }
//
//    @Override
//    public int getCount() {
//        return mItems.length;
//    }
//
//    @Override
//    public float getPageWidth(int position) {
//        return .9f;
//    }

    public WeakHashMap<Integer, BaseFragment> getFragments() {
        return mFragments;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}