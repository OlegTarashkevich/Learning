package com.prilaga.news.viewmodel

import androidx.lifecycle.ViewModel
import com.prilaga.news.data.SingleLiveEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.coroutines.CoroutineContext

/**
 * Created by Oleg Tarashkevich on 29/03/2019.
 */

open class WorkViewModel : ViewModel() {

    protected var viewModelJob = Job()
    protected val viewModelScope = CoroutineScope(Main + viewModelJob)
    val errorData = SingleLiveEvent<Throwable>()

    /**
     * TODO: Check for error handling: https://kotlinlang.org/docs/reference/coroutines/exception-handling.html
     *
     * Call the onError of handler in GlobalScope
     */
    val handler = CoroutineExceptionHandler { _, throwable ->
        doCoroutineWork({ errorData.callWithValue(throwable) }, GlobalScope, Main)
    }

    // Do work in Default
    fun <P> doWorkDefault(doOnAsyncBlock: suspend CoroutineScope.() -> P) {
        doCoroutineWork(doOnAsyncBlock, viewModelScope, Default)
    }

    // Do work in IO
    fun <P> doWorkIO(doOnAsyncBlock: suspend CoroutineScope.() -> P) {
        doCoroutineWork(doOnAsyncBlock, viewModelScope, IO)
    }

    // Do work in Main
    fun <P> doWorkInMainThread(doOnAsyncBlock: suspend CoroutineScope.() -> P) {
        doCoroutineWork(doOnAsyncBlock, viewModelScope, Main)
    }

    protected inline fun <P> doCoroutineWork(
        crossinline doOnAsyncBlock: suspend CoroutineScope.() -> P,
        coroutineScope: CoroutineScope,
        context: CoroutineContext
    ) {
        coroutineScope.launch(handler) {
            withContext(context) {
                doOnAsyncBlock.invoke(this)
            }
        }
    }

    // Cancel the job and all the children. No work can be performed after cancellation.
    fun cancelJob() {
        viewModelJob.cancel()
    }

    // Cancel children jobs only
    fun cancelJobChildren() {
        viewModelJob.cancelChildren()
    }

    open fun onError(throwable: Throwable) {
        doWorkInMainThread { errorData.callWithValue(throwable) }
    }

}