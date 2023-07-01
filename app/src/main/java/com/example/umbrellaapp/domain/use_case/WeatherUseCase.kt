package com.example.umbrellaapp.domain.use_case

import com.example.umbrellaapp.common.NetworkResponse
import com.example.umbrellaapp.domain.repository.WeatherItemRepository
import com.example.umbrellaapp.data.datasource.remote.model.SearchWeatherItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.internal.NopCollector.emit
import javax.inject.Inject

// API経由で天気情報を取得
class WeatherUseCase @Inject constructor(
    private val repository: WeatherItemRepository
){
    operator fun invoke(lat: Double,lon:Double): Flow<NetworkResponse<SearchWeatherItem>> = flow {
        try {
            emit(NetworkResponse.Loading<SearchWeatherItem>());
            val weatherItem = repository.searchWeatherItem(lat,lon)
            emit(NetworkResponse.Success<SearchWeatherItem>(weatherItem))
        } catch(e: Exception) {
            e.printStackTrace()
            emit(NetworkResponse.Failure<SearchWeatherItem>(e.message.toString()))
        }
    }


}