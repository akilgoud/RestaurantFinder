package com.cloudfull.restaraunts.di

import com.cloudfull.restaraunts.view.RestaurantFinderActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{
    @ContributesAndroidInjector
    abstract fun contributeMapsActivity(): RestaurantFinderActivity
}