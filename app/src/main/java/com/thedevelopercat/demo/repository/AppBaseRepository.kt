package com.thedevelopercat.demo.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.thedevelopercat.demo.AppBaseApplication
import com.thedevelopercat.sonic.rest.ApiClient
import com.thedevelopercat.sonic.rest.SonicRepository
import com.thedevelopercat.sonic.rest.SonicResponse

abstract class AppBaseRepository<Service> : SonicRepository<Service>(){

    init {
        service = ApiClient.client.create(getServiceClass())
    }

    override fun onInvalidRequest(
        requestType: Int,
        result: MutableLiveData<SonicResponse>) {

    }

    override fun onFailure(t: Throwable?, requestType: Int) {
    }

    override fun getApplication(): Application {
        return AppBaseApplication.instance
    }

    override fun getErrorResponseKeys(): Array<String> {
        return arrayOf("msg", "message", "error", "errorMessage", "error_message", "errormessage")
    }

}
