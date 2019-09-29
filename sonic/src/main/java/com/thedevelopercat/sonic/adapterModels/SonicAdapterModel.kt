package com.thedevelopercat.sonic.adapterModels

import java.io.Serializable
import kotlin.Int

interface SonicAdapterModel: Serializable {
    abstract fun getRecyclerViewType(): Int
}
