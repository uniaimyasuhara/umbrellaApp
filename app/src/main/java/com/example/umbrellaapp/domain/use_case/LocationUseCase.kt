package com.example.umbrellaapp.domain.use_case

import com.example.umbrellaapp.common.NetworkResponse
import com.example.umbrellaapp.domain.repository.LocationRepository
import com.example.umbrellaapp.data.datasource.remote.model.location.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke(prefecture:String,city:String): Flow<NetworkResponse<List<Location>?>> = flow {
        try {
            emit(NetworkResponse.Loading<List<Location>?>());
            val location = repository.searchLocation(prefecture,city)
            emit(NetworkResponse.Success<List<Location>?>(location))
        } catch(e: Exception) {
            e.printStackTrace()
            emit(NetworkResponse.Failure<List<Location>?>(e.message.toString()))
        }
    }
}