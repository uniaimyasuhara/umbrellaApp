package com.example.umbrellaapp.remote.api

import com.example.umbrellaapp.remote.data.SearchWeatherItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("onecall")
    suspend fun searchWeatherItem(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("appid") apiKey: String
    ): SearchWeatherItem
}