package com.cloudfull.restaraunts.model.repository



class RestaurantsRepository(private val repository: RestaurantsApiService) :
    RestaurantsApiService {
    override fun getNearByRestaurants(
        query: String,
        type: String,
        radius: String,
        location: String,
        key: String
    ) = repository.getNearByRestaurants(query, type, radius, location, key)

    override fun getRestaurantsDetails(
        placeId: String,
        fields: String,
        key: String
    ) = repository.getRestaurantsDetails(placeId, fields, key)
}