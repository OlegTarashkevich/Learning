package com.prilaga.news

import android.app.Application
import com.prilaga.news.di.mvvmModule
import com.prilaga.news.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Oleg Tarashkevich on 27/03/2019.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(repositoryModule, mvvmModule)
        }

    }

    
}