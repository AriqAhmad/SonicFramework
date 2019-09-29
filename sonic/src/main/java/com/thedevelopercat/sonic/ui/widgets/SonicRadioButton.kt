package com.thedevelopercat.sonic.ui.widgets

import android.content.Context
import android.util.AttributeSet

import androidx.appcompat.widget.AppCompatRadioButton

class SonicRadioButton(context: Context, attrs: AttributeSet? = null) :
    AppCompatRadioButton(context, attrs) {

    init {
        setCustomAttrs(context, attrs)
    }

    private fun setCustomAttrs(ctx: Context, attrs: AttributeSet?) {
    }
}
