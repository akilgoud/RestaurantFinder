package com.cloudfull.restaraunts.model.data

import com.cloudfull.restaraunts.model.data.entities.RestaurantDetails
import com.google.gson.annotations.SerializedName

data class RestaurantsDetailsResponse(
    @SerializedName("result") val result: RestaurantDetails
)