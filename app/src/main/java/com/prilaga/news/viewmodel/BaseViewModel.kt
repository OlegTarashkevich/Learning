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
    open fun onCreateView() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroyView() {
        cancelJob()
    }
}