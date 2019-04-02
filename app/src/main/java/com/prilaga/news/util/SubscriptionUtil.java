//package com.prilaga.news.util;
//
//import rx.Observable;
//import rx.Observer;
//import rx.Subscription;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
///**
// * Created by Oleg Tarashkevich on 31/03/2017.
// */
//
//public final class SubscriptionUtil {
//
//    public static <T> Subscription bindObservable(Observable<T> observable, Observer<T> observer) {
//        return observable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
//
//    public static String getCurrentThreadInfo() {
//        return Thread.currentThread().getName() + "(" + Thread.currentThread().getId() + ")";
//    }
//}
