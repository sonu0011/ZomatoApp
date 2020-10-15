package com.sonu.zomatoapp.data.repositories

import com.sonu.zomatoapp.data.network.MyApi
import com.sonu.zomatoapp.data.network.SafeApiRequest
import com.sonu.zomatoapp.data.network.responses.NearByRestaurantsResponse
import com.sonu.zomatoapp.data.network.responses.SearchedRestaurantResponse

class RestaurantsRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun fetchRestaurants(latitude: Double, longitude: Double): NearByRestaurantsResponse {
        return apiRequest { api.getRestaurants(latitude, longitude) }
    }

    suspend fun getSearchedRestaurants(
        latitude: Double,
        longitude: Double,
        q: String
    ): SearchedRestaurantResponse {
        return apiRequest { api.getSearchedRestaurants(q, latitude, longitude) }
    }
}