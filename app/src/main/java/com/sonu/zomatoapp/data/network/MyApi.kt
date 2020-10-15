package com.sonu.zomatoapp.data.network

import com.sonu.zomatoapp.data.network.responses.NearByRestaurantsResponse
import com.sonu.zomatoapp.data.network.responses.SearchedRestaurantResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {
    @GET("geocode")
    suspend fun getRestaurants(
        @Query("lat") lat: Double,
        @Query("long") long: Double
    ): Response<NearByRestaurantsResponse>

    @GET("search")
    suspend fun getSearchedRestaurants(
        @Query("q") query: String,
        @Query("lat") lat: Double,
        @Query("long") long: Double
    ): Response<SearchedRestaurantResponse>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
//                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl("https://developers.zomato.com/api/v2.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }

}

