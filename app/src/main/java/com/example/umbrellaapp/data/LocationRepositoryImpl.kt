package com.example.umbrellaapp.data

import com.example.umbrellaapp.domain.repository.LocationRepository
import com.example.umbrellaapp.remote.api.LocationApi
import com.example.umbrellaapp.remote.data.location.Location
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val LocationApi: LocationApi
) : LocationRepository{
    override suspend fun searchLocation(prefecture:String,city:String) : List<Location>?{
        val searchParam = "$prefecture $city"
        return LocationApi.searchLocation(searchParam,"json")
    }
}