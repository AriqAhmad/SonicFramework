package com.thedevelopercat.sonic.ui.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class SonicTextView(context: Context, attrs: AttributeSet? = null) :
    AppCompatTextView(context, attrs) {

    init {
        setCustomAttrs(context, attrs)
    }

    private fun setCustomAttrs(ctx: Context, attrs: AttributeSet?) {

    }
}
