package com.cloudfull.restaraunts.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cloudfull.restaraunts.di.viewmodel.ViewModelFactory
import com.cloudfull.restaraunts.di.viewmodel.ViewModelKey
import com.cloudfull.restaraunts.viewmodel.RestaurantFinderViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RestaurantFinderViewModel::class)
    abstract fun bindsPickUpLocationsViewModel(yourViewModel: RestaurantFinderViewModel): ViewModel

    // Factory
    @Binds
    abstract fun bindViewModelFactory(vmFactory: ViewModelFactory): ViewModelProvider.Factory
}