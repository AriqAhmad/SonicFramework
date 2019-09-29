package com.thedevelopercat.sonic.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.thedevelopercat.sonic.base.SonicApplication

open class SonicModel {

    fun getString(@StringRes stringRes: Int): String {
        return SonicApplication.instance.resources.getString(stringRes)
    }

    fun getColor(@ColorRes colorRes: Int): Int {
        return SonicApplication.instance.resources.getColor(colorRes)
    }
}
