package com.cloudfull.restaraunts.di
import com.cloudfull.restaraunts.RestaurantFinderApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, NetworkModule::class, RepositoryModule::class,
        ViewModelModule::class,ActivityModule::class]
)
interface AppComponent : AndroidInjector<RestaurantFinderApplication>
