package com.thedevelopercat.demo.repository

import android.app.Application
import com.thedevelopercat.demo.AppBaseApplication
import com.thedevelopercat.sonic.rest.SonicRepository

open class AppBaseRepository : SonicRepository(){

    override fun getApplication(): Application {
        return AppBaseApplication.instance
    }

}
