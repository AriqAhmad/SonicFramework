package com.thedevelopercat.demo.viewHolders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thedevelopercat.demo.models.user.request.UserDetailsRequest
import com.thedevelopercat.demo.models.user.response.UserDetailsResponse
import com.thedevelopercat.demo.repository.UserRepository
import com.thedevelopercat.sonic.rest.SonicResponse
import com.thedevelopercat.sonic.viewModels.SonicViewModel

class MainViewModel : SonicViewModel() {

    fun getUserDetails(): LiveData<SonicResponse> {
        return UserRepository.getUserDetails(UserDetailsRequest())
    }
}