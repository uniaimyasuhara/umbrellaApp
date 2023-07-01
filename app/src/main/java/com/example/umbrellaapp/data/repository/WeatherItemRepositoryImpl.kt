package com.example.umbrellaapp.data.repository

import com.example.umbrellaapp.domain.repository.WeatherItemRepository
import com.example.umbrellaapp.data.datasource.remote.api.Constants.API_KEY
import com.example.umbrellaapp.data.datasource.remote.api.WeatherApi
import com.example.umbrellaapp.data.datasource.remote.model.SearchWeatherItem
import javax.inject.Inject

class WeatherItemRepositoryImpl @Inject constructor (
    private  val api : WeatherApi
): WeatherItemRepository {
    override suspend fun searchWeatherItem(lat: Double, lon: Double, ): SearchWeatherItem? {
        return api.searchWeatherItem(lat, lon, "current,minutely", API_KEY)
    }
}