package com.thedevelopercat.demo.api.services

import androidx.annotation.Keep
import com.thedevelopercat.demo.api.EndPoints
import com.thedevelopercat.demo.models.user.response.UserDetailsResponse
import retrofit2.Call
import retrofit2.http.GET

@Keep
interface UserService {

    @GET(EndPoints.USER_DETAIL)
    fun getUserDetails(): Call<UserDetailsResponse>
}