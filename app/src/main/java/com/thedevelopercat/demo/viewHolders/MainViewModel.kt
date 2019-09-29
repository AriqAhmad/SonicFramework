package com.thedevelopercat.demo.viewHolders

import androidx.lifecycle.MutableLiveData
import com.thedevelopercat.demo.models.user.request.UserDetailsRequest
import com.thedevelopercat.demo.models.user.response.UserDetailsResponse
import com.thedevelopercat.demo.repository.UserRepository
import com.thedevelopercat.sonic.viewModels.SonicViewModel

class MainViewModel : SonicViewModel() {

    fun getUserDetails(): MutableLiveData<UserDetailsResponse> {
        return UserRepository.instance.getUserDetails(UserDetailsRequest())
    }
}