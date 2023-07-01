package com.example.umbrellaapp.data.datasource.remote.api

import com.example.umbrellaapp.data.datasource.remote.model.location.Location
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("search?")
    suspend fun searchLocation(
        @Query("q") q: String,
        @Query("format") format: String,
    ):List<Location>
}