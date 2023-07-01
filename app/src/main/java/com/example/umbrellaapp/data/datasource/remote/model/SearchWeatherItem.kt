package com.example.umbrellaapp.data.datasource.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchWeatherItem(
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)