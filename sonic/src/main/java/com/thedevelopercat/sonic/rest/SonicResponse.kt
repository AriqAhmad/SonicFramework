package com.thedevelopercat.sonic.rest

import com.thedevelopercat.sonic.model.SonicModel

open class SonicResponse: SonicModel() {
    var error: Throwable? = null
    var message: String? = null
    var errorMessage: String? = null
    var status: Int? = null
    var success: Boolean = false
}
