package com.thedevelopercat.sonic.base

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.thedevelopercat.sonic.rest.ApiClient
import com.thedevelopercat.sonic.utils.PreferencesUtils

abstract class SonicApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        ApiClient.initApiClient(getBaseUrl())
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        PreferencesUtils.initSharedPreferences(this)
    }

    companion object {
        lateinit var instance: SonicApplication
    }

    abstract fun getBaseUrl(): String
}
