package com.thedevelopercat.demo.models

import com.thedevelopercat.sonic.model.SonicModel

data class UserModel(
    var name: String? = null,
    var age: Int? = null
): SonicModel() {
}