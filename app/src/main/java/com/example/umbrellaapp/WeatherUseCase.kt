package com.example.umbrellaapp

import android.util.Log
import com.example.umbrellaapp.common.NetworkResponse
import com.example.umbrellaapp.domain.repository.WeatherItemRepository
import com.example.umbrellaapp.remote.data.SearchWeatherItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.internal.NopCollector.emit
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

// API経由で天気情報を取得
class WeatherUseCase @Inject constructor(
    private val repository: WeatherItemRepository
){
    operator fun invoke(lat: Double,lon:Double): Flow<NetworkResponse<SearchWeatherItem>> = flow {
        try {
            emit(NetworkResponse.Loading<SearchWeatherItem>());
            Log.d("Loading1", "loadingoading")
            val weatherItem = repository.searchWeatherItem(lat,lon)
            emit(NetworkResponse.Success<SearchWeatherItem>(weatherItem))
            Log.d("レスポンス", weatherItem.toString())
            Log.d("Success", "kd")
        } catch(e: Exception) {
            e.printStackTrace()
            Log.d("テストエラー",e.message.toString())
            emit(NetworkResponse.Failure<SearchWeatherItem>(e.message.toString()))
        }
    }
}