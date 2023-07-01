package com.example.umbrellaapp.data.datasource.remote.api

import com.example.umbrellaapp.data.datasource.remote.model.SearchWeatherItem
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