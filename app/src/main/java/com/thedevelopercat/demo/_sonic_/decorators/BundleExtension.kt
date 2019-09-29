package com.thedevelopercat.demo._sonic_.decorators

import android.os.Bundle
import com.thedevelopercat.demo._sonic_.constants.IntentConstants

fun Bundle.putNumber(number: Int): Bundle{
    this.putInt(IntentConstants.INT.name, number)
    return this
}

fun Bundle.getNumber(): Int{
    return this.getInt(IntentConstants.INT.name)
}