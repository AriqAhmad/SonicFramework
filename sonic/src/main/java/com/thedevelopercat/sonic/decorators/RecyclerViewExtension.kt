package com.thedevelopercat.sonic.decorators

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.thedevelopercat.sonic.recyclerView.CustomDividerItemDecoration
import com.thedevelopercat.sonic.recyclerView.VerticalSpaceItemDecoration
import com.thedevelopercat.sonic.utils.ConversionUtils

fun RecyclerView.addDivider(context: Context, spacing: Float = 8f, @DrawableRes layout: Int){
    this.addItemDecoration(CustomDividerItemDecoration(context, layout))
    this.addItemDecoration(VerticalSpaceItemDecoration(ConversionUtils.dp2px(spacing)))
}

fun RecyclerView.removeItemDecorations(){
    while (this.itemDecorationCount>0){
        this.removeItemDecorationAt(0)
    }
    this.invalidateItemDecorations()
}