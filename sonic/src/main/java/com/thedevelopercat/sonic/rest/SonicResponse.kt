package com.thedevelopercat.sonic.rest

import com.thedevelopercat.sonic.model.SonicModel

open class SonicResponse: SonicModel() {
    open var error: Throwable? = null
    open var message: String? = null
    open var errorMessage: String? = null
    open var status: Int? = null
    open var success: Boolean = false
}
