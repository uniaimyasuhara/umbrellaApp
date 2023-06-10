package com.example.umbrellaapp.data

import android.util.Log
import com.example.umbrellaapp.domain.repository.WeatherItemRepository
import com.example.umbrellaapp.remote.api.Constants.API_KEY
import com.example.umbrellaapp.remote.api.WeatherApi
import com.example.umbrellaapp.remote.data.SearchWeatherItem
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Moshi
import javax.inject.Inject

class WeatherItemRepositoryImpl @Inject constructor (
    private  val api : WeatherApi
): WeatherItemRepository {
    override suspend fun searchWeatherItem(lat: Double, lon: Double, ): SearchWeatherItem? {
        return api.searchWeatherItem(lat, lon, "current,minutely", API_KEY)
    }
}