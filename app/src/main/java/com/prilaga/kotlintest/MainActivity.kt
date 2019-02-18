package com.prilaga.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicLong

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        push_button.setOnClickListener { async() }
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

}
