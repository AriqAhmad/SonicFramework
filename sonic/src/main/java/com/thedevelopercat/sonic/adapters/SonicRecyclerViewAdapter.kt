package com.thedevelopercat.sonic.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.thedevelopercat.sonic.adapterModels.SonicAdapterModel
import com.thedevelopercat.sonic.viewHolders.SonicViewHolder

interface RecyclerViewItemClickListener {
    fun onItemClick(itemView: View, position: Int, obj: Any, actionType: Int)
}

abstract class BaseRecyclerViewAdapter<T : SonicAdapterModel>(context: Context, protected var list: ArrayList<T>) : androidx.recyclerview.widget.RecyclerView.Adapter<SonicViewHolder>() {
    private var inflater: LayoutInflater = LayoutInflater.from(context)

    var itemClickListener: RecyclerViewItemClickListener? = null
        private set

    fun setOnItemClickListener(clickInterface: RecyclerViewItemClickListener) {
        this.itemClickListener = clickInterface
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): SonicViewHolder {
        val view = inflater.inflate(getRecyclerViewLayout(type),
            parent, false)
        val viewHolder = getRecyclerViewHolder(view, type)
        viewHolder?.listener = itemClickListener
        return viewHolder ?: throw IllegalArgumentException("Invalid View Type")
    }

    override fun onBindViewHolder(holder: SonicViewHolder, position: Int) {
        holder.onBind(position, this.list[position])
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].getRecyclerViewType()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @LayoutRes abstract fun getRecyclerViewLayout(viewType: Int): Int
    abstract fun getRecyclerViewHolder(view: View, type: Int): SonicViewHolder?
}
