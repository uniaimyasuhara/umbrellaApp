package com.example.umbrellaapp.domain.use_case

import android.util.Log
import com.example.umbrellaapp.common.NetworkResponse
import com.example.umbrellaapp.domain.repository.LocationRepository
import com.example.umbrellaapp.remote.data.SearchWeatherItem
import com.example.umbrellaapp.remote.data.location.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke(prefecture:String,city:String): Flow<NetworkResponse<List<Location>?>> = flow {
        try {
            emit(NetworkResponse.Loading<List<Location>?>());
            Log.d("Loading1", "loadingoading")
            val location = repository.searchLocation(prefecture,city)
            emit(NetworkResponse.Success<List<Location>?>(location))
            Log.d("レスポンス", location.toString())
            Log.d("Success", "kd")
        } catch(e: Exception) {
            e.printStackTrace()
            Log.d("テストエラー",e.message.toString())
            emit(NetworkResponse.Failure<List<Location>?>(e.message.toString()))
        }
    }
}