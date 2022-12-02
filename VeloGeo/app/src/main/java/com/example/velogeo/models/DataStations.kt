package com.example.velogeo.models

import com.google.gson.annotations.SerializedName

data class DataStations(
    val lon: Double,
    val lat: Double,
    val name: String,
    val capacity:Double,
    val post_code:String,
    )
