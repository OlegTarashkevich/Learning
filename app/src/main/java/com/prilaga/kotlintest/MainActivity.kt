package com.prilaga.kotlintest

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
            // async()
            // rxTest()
//            testSubject()
            coroutinesTest()
        }
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

    fun async() {
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

//            withContext(Dispatchers.Main) {
//                blurred
////                delay(1000)
//                logThread("Dispatchers.Main")
//            }

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
        GlobalScope.launch {
            delay(1000L)
            logThread("runBlocking()")

            val job = GlobalScope.launch {
                delay(1000L)
                logThread("runBlocking()")
            }

            job.join()
        }


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

}
