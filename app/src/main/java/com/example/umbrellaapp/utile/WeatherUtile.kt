package com.example.umbrellaapp.utile

import android.util.Log
import com.example.umbrellaapp.WeatherUseCase
import com.example.umbrellaapp.common.NetworkResponse
import com.example.umbrellaapp.remote.data.Daily
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class WeatherUtile @Inject constructor(
    val weatherUseCase: WeatherUseCase,
) {

    var lat:Double = 0.0
    var lon:Double = 0.0

    /**
     * メッセージを返す
     */
    suspend fun getMessage(isToday:Boolean, targetLat: Double, targetLon:Double) : String{
        var pop = 0.0
        lat = targetLat
        lon = targetLon
        runBlocking {
            pop = getPop(isToday)
        }
        val message:String = if(pop <= 0.5){
            "傘要らんで"
        }else{
            "傘要るで"
        }

        return  message
    }

    /**
     * 洪水確率を取得
     */
    private suspend fun getPop(isToday:Boolean) : Double = withContext(Dispatchers.Default) {
        val weatherData: Flow<List<Daily>> = fetchWeatherInfo()
        var pop = 0.00
        var index = 1
        weatherData.collect {
            if(isToday){
                index = 0
            }
            pop = it[index].pop
        }
        pop
    }

    /**
     * 天気情報を取得
     * */
    private fun fetchWeatherInfo(): Flow<List<Daily>> = flow<List<Daily>> {

        weatherUseCase(lat, lon).collect { result ->
            when (result) {
                is NetworkResponse.Success -> {
                    val data = result.data?.daily ?: emptyList()
                    emit(data)
                }
                is NetworkResponse.Loading -> {
                    Log.d("ロード中", result.toString())
                }
                is NetworkResponse.Failure -> {
                    Log.d("失敗", result.error.toString())
                }
            }
        }
    }
}