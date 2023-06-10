package com.example.umbrellaapp.remote.api

import com.example.umbrellaapp.remote.data.SearchWeatherItem
import com.example.umbrellaapp.remote.data.location.Location
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("search?")
    suspend fun searchLocation(
//        @Query("country") country: String,
//        @Query("city") city: String,
        @Query("q") q: String,
        @Query("format") format: String,
    ):List<Location>
}