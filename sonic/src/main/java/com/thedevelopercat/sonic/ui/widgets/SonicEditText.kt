package com.thedevelopercat.sonic.ui.widgets

import android.content.Context
import android.util.AttributeSet

import androidx.appcompat.widget.AppCompatEditText

class SonicEditText(context: Context, attrs: AttributeSet? = null) :
    AppCompatEditText(context, attrs) {

    init {
        setCustomAttrs(context, attrs)
    }

    private fun setCustomAttrs(ctx: Context, attrs: AttributeSet?) {
    }
}
