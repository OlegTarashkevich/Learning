package com.prilaga.news.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.prilaga.news.data.NewsRepository
import com.prilaga.news.data.network.API
import com.prilaga.news.data.network.ApiService
import com.prilaga.news.data.network.HeadersInterceptor
import com.prilaga.news.data.network.RemoteDataSource
import com.prilaga.news.viewmodel.SourceViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Oleg Tarashkevich on 27/03/2019.
 */

// region Repository
val mvvmModule = module {
    viewModel { SourceViewModel(get()) }
}
// endregion

// region network
val repositoryModule = module {

    // create ApiService
    single { createWebService<ApiService>(getOkHttpClient(), API.PRODUCTION.baseUrl) }
    // setup RemoteDataSource
    single { RemoteDataSource(get()) }

    single { NewsRepository(get()) }

//    factory {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val builder = OkHttpClient.Builder()
//            .connectTimeout(10, TimeUnit.SECONDS)
//            .readTimeout(10, TimeUnit.SECONDS)
//            .writeTimeout(10, TimeUnit.SECONDS)
//            .addInterceptor(HeadersInterceptor())
//            .addInterceptor(loggingInterceptor)
//
//        val okHttpClient = builder.build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(API.PRODUCTION.baseUrl)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .client(okHttpClient)
//            .build()
//        retrofit.create(RemoteDataSource::class.java)
//    }
}

fun getOkHttpClient(): OkHttpClient {

    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val builder = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(HeadersInterceptor())
        .addInterceptor(loggingInterceptor)

    return builder.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}
// endregion