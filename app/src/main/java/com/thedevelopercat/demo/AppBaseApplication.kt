package com.thedevelopercat.demo

import com.thedevelopercat.demo.api.EndPoints
import com.thedevelopercat.sonic.base.SonicApplication

class AppBaseApplication: SonicApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: AppBaseApplication
    }

    override fun getBaseUrl(): String {
        return EndPoints.BASE_URL
    }
}