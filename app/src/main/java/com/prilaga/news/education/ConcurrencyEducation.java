package com.prilaga.news.education;

import android.util.Log;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Oleg Tarashkevich on 13/01/2019.
 */
public class ConcurrencyEducation {

    private static final String TAG = "Concurrency";

    public void init(){
        notSynchronizedSetExample();
        synchronizedSetExample();
    }

    public void notSynchronizedSetExample() {
        try {
            final AtomicInteger atomicInteger = new AtomicInteger();
            final Set<Integer> set = new HashSet<>();
            ExecutorService executorService = Executors.newFixedThreadPool(10);

            for (int i = 0; i < 1000; i++) {
                executorService.execute(new Runnable() {
                    @Override public void run() {
                        set.add(atomicInteger.incrementAndGet());
                    }
                });
            }

            executorService.shutdown();
            executorService.awaitTermination(1000, TimeUnit.SECONDS);

            Log.d(TAG, "not synchronized " + set.size());

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void synchronizedSetExample(){
       try {

           final AtomicInteger atomicInteger = new AtomicInteger();
           final Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());
           ExecutorService executorService = Executors.newFixedThreadPool(10);

           for (int i = 0; i < 1000; i++) {
               executorService.execute(new Runnable() {
                   @Override public void run() {
                       set.add(atomicInteger.incrementAndGet());
                   }
               });
           }

           executorService.shutdown();
           executorService.awaitTermination(1000, TimeUnit.SECONDS);

           Log.d(TAG, "synchronized " + set.size());

       } catch (Throwable e){
           e.printStackTrace();
       }
    }
}
