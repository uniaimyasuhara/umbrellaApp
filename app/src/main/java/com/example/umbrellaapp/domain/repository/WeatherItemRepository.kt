package com.example.umbrellaapp.domain.repository

import com.example.umbrellaapp.data.datasource.remote.model.SearchWeatherItem

interface WeatherItemRepository {
    suspend fun searchWeatherItem(lat: Double,lon:Double): SearchWeatherItem?
}