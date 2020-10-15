package com.sonu.zomatoapp.data.network.responses

import com.sonu.zomatoapp.data.model.NearbyRestaurant

data class NearByRestaurantsResponse(
    var nearby_restaurants: ArrayList<NearbyRestaurant>?
)