package com.cloudfull.restaraunts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cloudfull.restaraunts.model.data.Resource
import com.cloudfull.restaraunts.model.data.RestaurantsDetailsResponse
import com.cloudfull.restaraunts.model.data.RestaurantsResponse
import com.cloudfull.restaraunts.model.data.entities.Restaurant
import com.cloudfull.restaraunts.model.repository.RestaurantsRepository
import com.cloudfull.restaraunts.utils.getLatLongString
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class RestaurantFinderViewModel @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository,
    @Named("secret_key") val key: String
) : ViewModel() {
    var restaurantsResponseMap = MutableLiveData<Map<String, Restaurant>>()
    val restaurantsResponse = MutableLiveData<RestaurantsResponse>()
    val restaurantsDetailsResponse = MutableLiveData<RestaurantsDetailsResponse>()
    val status = MutableLiveData<Resource<Boolean>>()
    private val compositeDisposable = CompositeDisposable()

    fun getNearByRestaurants(query: String, location: String) {
        status.value = Resource.Loading(null)
        compositeDisposable.add(
            restaurantsRepository.getNearByRestaurants(
                query,
                "restaurant",
                "1500",
                location,
                key
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    status.value = Resource.Success(true)
                    restaurantsResponse.value = it
                    restaurantsResponseMap.value = convert(it.results)
                }, {
                    status.value = Resource.Error("Error")
                    restaurantsResponse.value = null
                    restaurantsResponseMap.value = emptyMap()
                })
        )
    }

    fun getRestaurantDetails(id: String) {
        status.value = Resource.Loading(null)
        compositeDisposable.add(
            restaurantsRepository.getRestaurantsDetails(
                id,
                "formatted_phone_number,website",
                key
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    status.value = Resource.Success(true)
                    restaurantsDetailsResponse.value = it
                }, {
                    status.value = Resource.Error("Error")
                    restaurantsDetailsResponse.value = null
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    private fun convert(restaurants: List<Restaurant>): Map<String, Restaurant> {
        val map = HashMap<String, Restaurant>()
        for (restaurant in restaurants) {
            map[getLatLongString(
                restaurant.geometry.location.lat,
                restaurant.geometry.location.lng
            )] = restaurant
        }
        return map
    }
}