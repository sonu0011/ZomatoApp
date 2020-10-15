package com.sonu.zomatoapp.data.model

data class UserRating(
    var aggregate_rating: String?,
    var rating_color: String?,
    var rating_obj: RatingObj?,
    var rating_text: String?,
    var votes: Int?
)