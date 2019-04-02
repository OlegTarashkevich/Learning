package com.prilaga.news.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.prilaga.news.data.SingleLiveEvent

/**
 * Created by Oleg Tarashkevich on 29/03/2019.
 */
open class BaseViewModel : WorkViewModel(), LifecycleObserver { //, KoinComponent {

//    val repository by inject<NewsRepository>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected open fun onCreateView() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected open fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected open fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected open fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected open fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected open fun onDestroyView() {
        cancelJob()
    }
}