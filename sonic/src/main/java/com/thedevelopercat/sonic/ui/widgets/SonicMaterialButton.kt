package com.thedevelopercat.sonic.ui.widgets

import android.content.Context
import androidx.annotation.ColorInt
import com.google.android.material.button.MaterialButton
import android.util.AttributeSet
import com.thedevelopercat.sonic.R

class SonicMaterialButton(context: Context, attrs: AttributeSet? = null) :
    MaterialButton(context, attrs) {

    @ColorInt
    private var enabledBackgroundColor = -1
    @ColorInt
    private var disabledBackgroundColor = -1
    @ColorInt
    private var enabledTextColor = -1
    @ColorInt
    private var disabledTextColor = -1

    init {
        setCustomAttrs(context, attrs)
    }

    private fun setCustomAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context
                .theme
                .obtainStyledAttributes(attrs, R.styleable.SonicMaterialButton, 0, 0)
        enabledBackgroundColor = typedArray.getColor(
                R.styleable.SonicMaterialButton_enabledBackgroundColor,
                -1)
        disabledBackgroundColor = typedArray.getColor(
                R.styleable.SonicMaterialButton_disabledBackgroundColor,
                -1)
        enabledTextColor = typedArray.getColor(
                R.styleable.SonicMaterialButton_enabledTextColor,
                -1)
        disabledTextColor = typedArray.getColor(
                R.styleable.SonicMaterialButton_disabledTextColor,
                -1)

        setEnabled(isEnabled)

        requestLayout()
        invalidate()
        typedArray.recycle()
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        isClickable = enabled

        if (enabled) {
            if (enabledBackgroundColor != -1) {
                this.setBackgroundColor(enabledBackgroundColor)
            }
            if (enabledTextColor != -1) {
                this.setTextColor(enabledTextColor)
            }
        } else {
            if (disabledBackgroundColor != -1) {
                this.setBackgroundColor(disabledBackgroundColor)
            }
            if (disabledTextColor != -1) {
                this.setTextColor(disabledTextColor)
            }
        }
    }
}
