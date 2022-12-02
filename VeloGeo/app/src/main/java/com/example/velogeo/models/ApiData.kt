package com.example.velogeo.models

import com.google.gson.annotations.SerializedName

data class ApiData(
    @SerializedName("stations") val stations: Array<DataStations>? = null,

    )
