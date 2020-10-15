package com.sonu.zomatoapp.data.model

data class Location(
    var address: String?,
    var city: String?,
    var city_id: Int?,
    var country_id: Int?,
    var latitude: String?,
    var locality: String?,
    var locality_verbose: String?,
    var longitude: String?,
    var zipcode: String?
)