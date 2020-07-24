package com.cloudfull.restaraunts

import android.app.Application
import com.cloudfull.restaraunts.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class RestaurantFinderApplication : Application(), HasAndroidInjector {
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create().inject(this)
    }
    @Inject lateinit var androidInjector : DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}