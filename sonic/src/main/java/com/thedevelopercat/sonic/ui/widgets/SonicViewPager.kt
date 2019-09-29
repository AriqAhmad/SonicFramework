package com.thedevelopercat.sonic.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class SonicViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    private var disableScrolling = false

    val isScrollingEnabled: Boolean
        get() = !disableScrolling


    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return !disableScrolling && super.onInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return !disableScrolling && super.onTouchEvent(event)
    }

    fun setDisableScrolling(disableScrolling: Boolean) {
        this.disableScrolling = disableScrolling
        requestLayout()
        invalidate()
    }
}
