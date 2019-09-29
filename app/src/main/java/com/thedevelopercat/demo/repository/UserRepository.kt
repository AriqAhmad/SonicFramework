package com.thedevelopercat.demo.repository

import androidx.lifecycle.MutableLiveData
import com.thedevelopercat.demo.R
import com.thedevelopercat.demo.api.services.UserService
import com.thedevelopercat.demo.models.user.request.UserDetailsRequest
import com.thedevelopercat.demo.models.user.response.UserDetailsResponse
import com.thedevelopercat.sonic.rest.ApiClient

class UserRepository private constructor() : AppBaseRepository() {

    companion object {
        val instance = UserRepository()
        val service: UserService = ApiClient.client.create(UserService::class.java)
    }

    fun getUserDetails(request: UserDetailsRequest): MutableLiveData<UserDetailsResponse> {
        val response = makeRequest(service.getUserDetails(), 1)

        response.value?.error?.let {
            response.value = parseLocalJson(R.raw.user, UserDetailsResponse::class.java)
        }
        return response
    }

}