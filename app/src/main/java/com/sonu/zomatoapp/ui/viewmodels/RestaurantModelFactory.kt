package com.sonu.zomatoapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sonu.zomatoapp.data.repositories.RestaurantsRepository

@Suppress("UNCHECKED_CAST")
class RestaurantModelFactory(
    private val repository: RestaurantsRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RestaurantViewModel(repository) as T
    }
}