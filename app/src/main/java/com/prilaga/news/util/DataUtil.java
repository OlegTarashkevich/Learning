package com.prilaga.news.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RawRes;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.experimental.Accessors;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import java.io.*;

/**
 * Created by Oleg Tarashkevich on 03/04/2017.
 */

@Accessors(prefix = "m")
public final class DataUtil {

    private Context mContext;
    @Getter private TinyDB mTinyDB;
    private Gson mGson;

    public DataUtil(Context context, Gson gson) {
        mContext = context;
        mTinyDB = new TinyDB(context);
        mGson = gson;
    }

    /**
     * Serialize & Deserialize
     */

    @NonNull
    public String serialize(Object object) {
        String json = mGson.toJson(object);
        return json;
    }

    @NonNull
    public <L> L deserialize(String json, Class<L> tClass) {
        L object = mGson.fromJson(json, tClass);
        return object;
    }

    /**
     * Save & Load object
     */

    @NonNull
    public void save(Object object, String key) {
        String json = serialize(object);
        mTinyDB.putString(key, json);
    }

    @NonNull
    public <L> L load(Class<L> tClass, String key) {
        String json = mTinyDB.getString(key, "");
        L object = deserialize(json, tClass);
        return object;
    }

    @NonNull
    public <L> L load(Class<L> tClass, String key, L defaultObject) {
        String json = mTinyDB.getString(key, "");
        L object = deserialize(json, tClass);
        if (object == null)
            object = defaultObject;
        return object;
    }

    @NonNull
    public <L> Observable<L> objectLoadObservable(final Class<L> tClass, final String key, final L defaultObject) {
        return Observable.create(new Observable.OnSubscribe<L>() {
            @Override
            public void call(Subscriber<? super L> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        L object = load(tClass, key);
                        if (object == null)
                            object = defaultObject;
                        subscriber.onNext(object);
                        subscriber.onCompleted();
                    }
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    /**
     * Load data from raw resources. Useful from mock testing.
     */
    @NonNull
    public <T> Observable<T> getResponseFromResObservable(final Class<T> clazz, @RawRes int rawId) {
        return getRawStringObservable(rawId).map(new Func1<String, T>() {
            @Override
            public T call(String s) {
                return deserialize(s, clazz);
            }
        });
    }

    @NonNull
    private Observable<String> getRawStringObservable(@RawRes final int rawId) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) return;

                try {
                    String jsonString = readJsonFromRes();
                    subscriber.onNext(jsonString);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }

            private String readJsonFromRes() throws IOException {
                InputStream is = mContext.getResources().openRawResource(rawId);
                Writer writer = new StringWriter();
                char[] buffer = new char[1024];
                try {
                    Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    int n;
                    while ((n = reader.read(buffer)) != -1) {
                        writer.write(buffer, 0, n);
                    }
                } finally {
                    is.close();
                }

                return writer.toString();
            }
        });
    }
}
