package com.example.velogeo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Network {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connectivityManager.allNetworks.firstOrNull { network ->
            val networkInfo = connectivityManager.getNetworkInfo(network)
            networkInfo!!.isConnected
        } != null
    }
}