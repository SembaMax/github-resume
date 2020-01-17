package com.semba.githubresume.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by SeMbA on 2019-12-07.
 */
object ConnectivityManager {

    fun isConnected(context: Context): Boolean
    {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo?.isConnectedOrConnecting ?: false
    }
}