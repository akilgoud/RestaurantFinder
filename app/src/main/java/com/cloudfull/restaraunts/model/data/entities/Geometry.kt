package com.cloudfull.restaraunts.model.data.entities
import com.google.gson.annotations.SerializedName

data class Geometry(
    @SerializedName("location") val location: Location
)