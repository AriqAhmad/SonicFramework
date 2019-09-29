package com.thedevelopercat.sonic.ui.widgets

import android.content.Context
import android.util.AttributeSet

import androidx.appcompat.widget.AppCompatCheckBox

class SonicCheckBox(context: Context, attrs: AttributeSet? = null) :
    AppCompatCheckBox(context, attrs) {

    init {
        setCustomAttrs(context, attrs)
    }

    private fun setCustomAttrs(ctx: Context, attrs: AttributeSet?) {
    }
}
