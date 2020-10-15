package com.sonu.zomatoapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sonu.zomatoapp.data.network.responses.NearByRestaurantsResponse
import com.sonu.zomatoapp.data.network.responses.SearchedRestaurantResponse
import com.sonu.zomatoapp.data.repositories.RestaurantsRepository
import com.sonu.zomatoapp.util.Coroutines

class RestaurantViewModel(
    private val repository: RestaurantsRepository
) : ViewModel() {
    val restaurants = MutableLiveData<NearByRestaurantsResponse>()
    val searchedrestaurants = MutableLiveData<SearchedRestaurantResponse>()

    fun getRestaurants(lat: Double, long: Double) {
        Coroutines.main {
            restaurants.value = repository.fetchRestaurants(lat, long)
        }
    }

    fun getSearchedRestaurants(lat: Double, long: Double, q: String) {
        Coroutines.main {
            searchedrestaurants.value = repository.getSearchedRestaurants(lat, long, q)
        }
    }
}
