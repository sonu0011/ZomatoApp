package com.sonu.zomatoapp.data.network.responses

import com.sonu.zomatoapp.data.model.NearbyRestaurant
import com.sonu.zomatoapp.data.model.Restaurant

data class SearchedRestaurantResponse(
    var restaurants: ArrayList<NearbyRestaurant>?,
    var results_found: Int?,
    var results_shown: Int?,
    var results_start: Int?
)