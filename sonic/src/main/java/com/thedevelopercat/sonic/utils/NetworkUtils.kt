package com.thedevelopercat.sonic.utils

import android.content.Context
import android.net.ConnectivityManager
import com.thedevelopercat.sonic.base.SonicApplication


object NetworkUtils {

    fun isNetworkConnected(): Boolean{
        val connectivityManager = SonicApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected

    }

}
