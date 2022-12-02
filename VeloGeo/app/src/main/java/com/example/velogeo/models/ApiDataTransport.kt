package com.example.velogeo.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ApiDataTransport(
    //val data : DataStations?,
    val data: ApiData,
    val test:String?,


    ):Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("data"),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(test)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApiDataTransport> {
        override fun createFromParcel(parcel: Parcel): ApiDataTransport {
            return ApiDataTransport(parcel)
        }

        override fun newArray(size: Int): Array<ApiDataTransport?> {
            return arrayOfNulls(size)
        }
    }

}

