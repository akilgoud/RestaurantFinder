package com.cloudfull.restaraunts.model.data

import com.cloudfull.restaraunts.model.data.entities.Restaurant
import com.google.gson.annotations.SerializedName

data class RestaurantsResponse(
    @SerializedName("results") val results: List<Restaurant>
)