package com.prilaga.news.util;

import android.util.Log;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

public final class Logger implements HttpLoggingInterceptor.Logger {

    public static final String TAG = "LOG";
    public static final String REST_TAG = "Rest";
    private static boolean showLog = false;

    @Override
    public void log(String message) {
      d(REST_TAG, message);
    }

    /**
     * Enables or disables logging.
     *
     * @param enable Set to true if logging should be enabled, false to disable logging.
     */
    public static void enableLogging(boolean enable) {
        showLog = enable;
        i("enableLogging: " + enable);
    }

    /**
     * Set an info log message.
     *
     * @param msg Log message to output to the console.
     */
    public static void i(String msg) {
        if (showLog)
            Log.i(TAG, msg);
    }

    /**
     * Set an error log message.
     *
     * @param msg Log message to output to the console.
     */
    public static void e(String msg) {
        if (showLog)
            e(TAG, msg);
    }

    public static void e(Throwable throwable) {
        Log.e(TAG, throwable.getMessage(), throwable);
    }

    public static void e(String msg, Throwable throwable) {
        if (showLog)
            e(TAG, msg, throwable);
    }

    public static void e(String tag, String msg, Throwable throwable) {
        if (showLog)
            Log.e(tag, msg, throwable);
    }

    public static void e(String tag, String msg) {
        if (showLog)
            Log.e(tag, msg);
    }

    /**
     * Set a warning log message.
     *
     * @param msg Log message to output to the console.
     */
    public static void w(String msg) {
        if (showLog)
            Log.w(TAG, msg);
    }

    /**
     * Set a debug log message.
     *
     * @param msg Log message to output to the console.
     */
    public static void d(String msg) {
        if (showLog)
            Log.d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (showLog)
            Log.d(tag, msg);
    }

    /**
     * Set a verbose log message.
     *
     * @param msg Log message to output to the console.
     */
    public static void v(String msg) {
        if (showLog)
            Log.v(TAG, msg);
    }
}
