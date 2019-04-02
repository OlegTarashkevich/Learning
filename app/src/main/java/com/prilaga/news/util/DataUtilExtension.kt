//package com.prilaga.news.util
//
//import android.content.Context
//import android.support.annotation.RawRes
//import com.google.gson.Gson
//import com.prilaga.data.utils.DataUtil
//import lombok.Getter
//import lombok.experimental.Accessors
//import rx.Observable
//import rx.Subscriber
//import rx.functions.Func1
//
//import java.io.*
//
///**
// * Created by Oleg Tarashkevich on 03/04/2017.
// */
//
//
//
//class DataUtilExtension(private val mContext: Context, private val mGson: Gson) {
//
//
//    /**
//     * Serialize & Deserialize
//     */
//
//    fun serialize(`object`: Any): String {
//        return mGson.toJson(`object`)
//    }
//
//    fun <L> deserialize(json: String, tClass: Class<L>): L {
//        return mGson.fromJson(json, tClass)
//    }
//
//    /**
//     * Save & Load object
//     */
//
//    fun DataUtil.save(`object`: Any, key: String) {
//        val json = serialize(`object`)
//        tinyDB.putString(key, json)
//    }
//
//    fun <L> DataUtil.load(tClass: Class<L>, key: String): L {
//        val json = tinyDB.getString(key, "")
//        return deserialize(json, tClass)
//    }
//
//    fun <L> DataUtil.load(tClass: Class<L>, key: String, defaultObject: L): L? {
//        val json = tinyDB.getString(key, "")
//        var `object`: L? = deserialize(json, tClass)
//        if (`object` == null)
//            `object` = defaultObject
//        return `object`
//    }
//
//
//
//}
