package com.example.umbrellaapp.data.datasource.remote.model.location

data class Location(
    val boundingbox: List<String>,
    val `class`: String,
    val display_name: String,
    val importance: Double,
    val lat: String,
    val licence: String,
    val lon: String,
    val osm_id: String,
    val osm_type: String,
    val place_id: String,
    val type: String
)