package com.thedevelopercat.demo.models.user.response

import com.thedevelopercat.demo.models.UserModel
import com.thedevelopercat.sonic.rest.SonicResponse

data class UserDetailsResponse(
    val data: UserModel? = null
): SonicResponse()