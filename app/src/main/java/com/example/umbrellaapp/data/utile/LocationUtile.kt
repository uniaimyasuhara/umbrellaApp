package com.example.umbrellaapp.data.utile

import android.util.ArrayMap
import android.util.Log
import com.example.umbrellaapp.common.NetworkResponse
import com.example.umbrellaapp.domain.use_case.LocationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationUtile @Inject constructor(
    val locationUseCase: LocationUseCase,
)
{
    private val locationMap = ArrayMap<String,String>()

    suspend fun getLocation(prefecture:String, city:String):ArrayMap<String,String>{
        withContext(Dispatchers.IO){
            fetchLocation(prefecture, city)
        }
        return  locationMap
    }

    suspend fun fetchLocation(country:String, city:String){

        locationUseCase(country,city).collect {result->
            when(result){
                is NetworkResponse.Success -> {
                    // 成功
                    val location = result.data?.get(0)
                    locationMap["lat"] = location?.lat.toString()
                    locationMap["lon"] = location?.lon.toString()
                    Log.d("成功",result.toString())
                }
                is NetworkResponse.Loading -> {
                    // ロード中
                    Log.d("ロード中",result.toString())
                }
                is NetworkResponse.Failure -> {
                    // 失敗
                    Log.d("エラー",result.error.toString())
                }
            }
        }
    }
}