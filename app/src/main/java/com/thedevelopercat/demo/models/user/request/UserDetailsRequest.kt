package com.thedevelopercat.demo.models.user.request

import com.thedevelopercat.sonic.rest.SonicRequest

data class UserDetailsRequest(
    val id: String? = null
): SonicRequest()