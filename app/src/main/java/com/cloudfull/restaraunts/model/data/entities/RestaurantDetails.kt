package com.cloudfull.restaraunts.model.data.entities

import com.google.gson.annotations.SerializedName

data class RestaurantDetails(
    @SerializedName("formatted_phone_number") val formattedPhoneNumber: String?,
    @SerializedName("website") val website: String?
)