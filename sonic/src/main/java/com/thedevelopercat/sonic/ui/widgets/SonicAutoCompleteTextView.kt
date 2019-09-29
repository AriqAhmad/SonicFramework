package com.thedevelopercat.sonic.ui.widgets

import android.content.Context
import android.util.AttributeSet

import androidx.appcompat.widget.AppCompatAutoCompleteTextView

class SonicAutoCompleteTextView(context: Context, attrs: AttributeSet? = null) :
    AppCompatAutoCompleteTextView(context, attrs) {

    init {
        setCustomAttrs(context, attrs)
    }

    private fun setCustomAttrs(ctx: Context, attrs: AttributeSet?) {

    }
}
