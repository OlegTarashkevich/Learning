package com.prilaga.news.di

/**
 * Created by Oleg Tarashkevich on 27/03/2019.
 */

//val remoteDataSourceModule = applicationC

val rxModule = applicationContext {
    // provided components
    bean { ApplicationSchedulerProvider() as SchedulerProvider }
}