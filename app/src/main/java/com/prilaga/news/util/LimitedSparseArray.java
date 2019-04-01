package com.prilaga.news.util;

import android.util.SparseArray;

/**
 * Created by Oleg Tarashkevich on 11/04/2017.
 */

public class LimitedSparseArray<E> extends SparseArray<E> {

    private int mInitialCapacity;

    public LimitedSparseArray(int initialCapacity) {
        super(initialCapacity);
        mInitialCapacity = initialCapacity;
    }

    @Override
    public void put(int key, E value) {
        if (size() < mInitialCapacity)
            super.put(key, value);
    }
}
