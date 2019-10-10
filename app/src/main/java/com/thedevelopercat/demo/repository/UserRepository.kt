package com.thedevelopercat.demo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thedevelopercat.demo.api.services.UserService
import com.thedevelopercat.demo.models.user.request.UserDetailsRequest
import com.thedevelopercat.demo.models.user.response.UserDetailsResponse
import com.thedevelopercat.sonic.rest.ApiClient
import com.thedevelopercat.sonic.rest.SonicResponse

object UserRepository : AppBaseRepository<UserService>() {

    override fun getServiceClass(): Class<UserService> {
        return UserService::class.java
    }

    fun getUserDetails(request: UserDetailsRequest): LiveData<SonicResponse> {
        return makeRequest(service?.getUserDetails(), 1)
    }

}