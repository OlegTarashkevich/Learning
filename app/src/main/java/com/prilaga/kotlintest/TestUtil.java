package com.prilaga.kotlintest;

import android.os.Handler;
import android.util.Log;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Oleg Tarashkevich on 27/02/2019.
 */
public class TestUtil {

    public static final String TAG = "new_test";

    public void test(){
        final BehaviorSubject<Object> subject = BehaviorSubject.create();
        subject
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        logThread("doOnNext");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new DisposableObserver() {
                    @Override
                    public void onComplete() {
                        logThread("onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        logThread("onNext");
                    }
                });
        subject.onNext("str");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                subject.onNext("str");
            }
        }, 1000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                subject.onNext("str");
            }
        }, 2000);
    }

    public static void logThread(String name){
        Log.d(TAG, name + " " + Thread.currentThread().getName());
    }

}
