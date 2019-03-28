package com.prilaga.news.data.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

import java.io.IOException

/**
 * Created by Oleg Tarashkevich on 28.03.19.
 */

class HeadersInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
            .header("X-Api-Key", API.API_KEY)
        return chain.proceed(builder.build())
    }
}
