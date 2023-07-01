package com.example.umbrellaapp.domain.repository

import com.example.umbrellaapp.data.datasource.remote.model.location.Location

interface LocationRepository {
    suspend fun searchLocation(prefecture :String,city:String): List<Location>?
}