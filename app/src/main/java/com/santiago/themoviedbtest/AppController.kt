package com.santiago.themoviedbtest

import android.app.Activity
import android.app.Application
import com.santiago.themoviedbtest.di.componet.DaggerAppComponent


import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AppController : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()


    }
}