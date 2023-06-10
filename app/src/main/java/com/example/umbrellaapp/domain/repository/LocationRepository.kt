package com.example.umbrellaapp.domain.repository

import com.example.umbrellaapp.common.Prefecture
import com.example.umbrellaapp.remote.data.SearchWeatherItem
import com.example.umbrellaapp.remote.data.location.Location

interface LocationRepository {
    suspend fun searchLocation(prefecture :String,city:String): List<Location>?
}