package com.prilaga.news.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.prilaga.news.R
import com.prilaga.news.education.delegate.BaseImpl
import com.prilaga.news.education.delegate.Derived
import com.prilaga.news.education.delegate.Openable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicLong

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "new_test"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        push_button.setOnClickListener {
            try {
                test12()
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    fun delegateLesson() {
        val b = BaseImpl(10)
        b.printIt()

        val o = object : Openable {
            var z: String? = null
            override fun openIt() {
                print("open $z")
            }
        }

        val d = Derived(b, o)
        d.printIt()
        d.openIt()
    }

    fun rxTest() {
        Observable.just("Test")
            .map {
                Log.d(TAG, Thread.currentThread().getName())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map {
                // blurImageSync(it)
                Log.d(TAG, Thread.currentThread().getName())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                // displayImage(it)
                Log.d(TAG, Thread.currentThread().getName())
            }

    }

    fun testSubject() {
        val subject = BehaviorSubject.create<Any>()

        subject
            .doOnNext { logThread("doOnNext") }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .subscribe(object : DisposableObserver<Any>() {
                override fun onComplete() {
                    logThread("onComplete")
                }

                override fun onError(e: Throwable) {}
                override fun onNext(o: Any) {
                    logThread("onNext")
                }
            })

        subject.onNext("str")

        val handler = Handler()
        handler.postDelayed({ subject.onNext("str") }, 1000)
        handler.postDelayed({ subject.onNext("str") }, 2000)
    }

    fun logThread(name: String) {
        Log.d(TAG, name + " " + Thread.currentThread().name)
    }

    fun doIt() {

        println("Start")

        // Start a coroutine
        GlobalScope.launch {
            delay(1000)
            println("Hello")
        }

//        Thread.sleep(2000) // wait for 2 seconds
        println("Stop")

    }

    fun fasterThanTreads() {

        val value = AtomicLong()
        for (i in 1..1_000_000L) {
            GlobalScope.launch { value.getAndAdd(i) }
        }

        println(value.get())
    }

    fun asyncFun() {
        println("Start")

        val deferred = (1..1_000_000).map { n ->
            GlobalScope.async {
                n
            }
        }

        runBlocking {
            val sum = deferred.sumBy { it.await() }
            println("sum: $sum")
        }

        println("Stop")
    }

    fun coroutinesTest() {
        val job: Job = GlobalScope.launch(Dispatchers.IO) {

            //            val image = api.fetchImageAsync(url).await()
            logThread("Dispatchers.IO")
            delay(1000)

            val blurred = withContext(Dispatchers.Default) {
                //                image.blur()
                delay(1000)
                logThread("Dispatchers.Default")
            }

            withContext(Dispatchers.Main) {
                blurred
//                delay(1000)
                logThread("Dispatchers.Main")
            }

//            yield()

            runBlocking {
                delay(1000)
                logThread("runBlocking()")
            }
        }

        job.start()
        logThread("job.start()")
    }

    fun test2() {

        logThread("test2 1")

        GlobalScope.launch {
            delay(1000L)
            logThread("test2 2")
        }

        logThread("test2 3")
        runBlocking {
            delay(2000L)
            logThread("test2 4")
        }
    }

    fun test3() = runBlocking {

        logThread("test3 1")

        // Launch new coroutine in background and continue
        GlobalScope.launch() {
            delay(1000L)
            logThread("test3 2")
        }

        delay(2000L)
        logThread("test3 3")
    }

    fun test4() = runBlocking {
        logThread("test4 1")

        val job = GlobalScope.launch {
            delay(1000L)
            logThread("test4 2")
        }

        logThread("test4 3")

        job.join() // pause the current coroutine and perform the job, then continue current coroutine

        logThread("test4 4")
    }

    fun test5() = runBlocking {
        logThread("test5 1")

        launch {
            delay(1000L)
            logThread("test5 2")
        }

        logThread("test5 3")
    }

    fun test6() = runBlocking {
        logThread("test6 1")

        launch {
            delay(200L)
            logThread("test6 2")
        }

        logThread("test6 3")

        coroutineScope {

            logThread("test6 4")

            launch {
                delay(500L)
                logThread("test6 5")
            }

            delay(100L)
            logThread("test6 6")
        }

        logThread("test6 7")
    }

    fun test7() = runBlocking {
        launch { doWorld() }
        logThread("test6 1")
    }

    suspend fun doWorld() {
        delay(1000L)
        logThread("test7 2")

    }

    fun test8() = GlobalScope.launch(Dispatchers.Main) {
        logThread("test8 1")

        val value1 = withContext(Dispatchers.Default) {
            logThread("test8 2")
            "value_1"
        }
        val value2 = withContext(Dispatchers.Default) {
            logThread("test8 3")
            "value_2"
        }
        val value3 = withContext(Dispatchers.IO) {
            logThread("test8 4")
            "$value1 and $value2"
        }

        logThread("test8 5")
        logThread(value3)
    }

    /**
     * Cancellation & timeouts
     * https://github.com/Kotlin/kotlinx.coroutines/blob/master/docs/cancellation-and-timeouts.md#cancellation-and-timeouts
     */

    fun test9() = runBlocking {
        logThread("test9 1")

        val job = launch {
            repeat(1000) { i ->
                logThread("test9 i:$i")
                delay(500L)
            }
        }

        delay(3000L)
        logThread("test9 2")
        job.cancel()
        job.join()
        logThread("test9 3")
    }

    // Cancellation is cooperative
    fun test10() = runBlocking {
        val startTime = System.currentTimeMillis();
        val job = GlobalScope.launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) { // computation loop, just wastes CPU
                // print a message twice a second
                if (System.currentTimeMillis() >= nextPrintTime) {
                    logThread("I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }

        delay(1300L) // delay a bit
        logThread("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        logThread("main: Now I can quit.")
    }

    // Closing resources with finally
    fun test11() = runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    logThread("I'm sleeping $i ...")
                    delay(500L)
                }
            } catch (e: Throwable) {
                logThread(e.toString())
            } finally {
                logThread("I'm running finally")
            }
        }
        delay(1300L) // delay a bit
        logThread("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        logThread("main: Now I can quit.")
    }

    fun test12() = runBlocking {

        val job = launch {
            try {
                repeat(1000) { i ->
                    logThread("I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                logThread("I'm running finally")
                delay(1000L)
                logThread("And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }

        delay(1300L) // delay a bit
        logThread("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        logThread("main: Now I can quit.")

    }

}
