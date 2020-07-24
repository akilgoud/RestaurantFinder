package com.cloudfull.restaraunts.model.repository

import com.cloudfull.restaraunts.model.data.RestaurantsDetailsResponse
import com.cloudfull.restaraunts.model.data.RestaurantsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantsApiService {
    @GET("place/nearbysearch/json")
    fun getNearByRestaurants(@Query("keyword") keyword:String,
                             @Query("type") type:String,
                             @Query("radius") radius:String,
                             @Query("location") location:String,
                             @Query("key") key:String):Single<RestaurantsResponse>
    @GET("place/details/json")
    fun getRestaurantsDetails(@Query("place_id") placeId: String,
                              @Query("fields") fields:String,
                              @Query("key") key:String):Single<RestaurantsDetailsResponse>
}