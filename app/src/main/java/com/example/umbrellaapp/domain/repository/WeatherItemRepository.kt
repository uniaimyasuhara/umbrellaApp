package com.example.umbrellaapp.domain.repository

import com.example.umbrellaapp.remote.data.SearchWeatherItem

interface WeatherItemRepository {
    suspend fun searchWeatherItem(lat: Double,lon:Double): SearchWeatherItem?
}