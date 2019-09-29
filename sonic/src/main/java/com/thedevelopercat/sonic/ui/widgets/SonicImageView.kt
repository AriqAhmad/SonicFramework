package com.thedevelopercat.sonic.ui.widgets

import android.content.Context
import android.util.AttributeSet

import androidx.appcompat.widget.AppCompatImageView

class SonicImageView(context: Context, attrs: AttributeSet? = null) :
    AppCompatImageView(context, attrs) {

    init {
        setCustomAttrs(context, attrs)
    }

    private fun setCustomAttrs(ctx: Context, attrs: AttributeSet?) {

    }
}
