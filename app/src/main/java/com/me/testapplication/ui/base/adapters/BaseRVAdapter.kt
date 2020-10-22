package com.me.testapplication.ui.base.adapters

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRVAdapter<D : DiffComparable, VH : RecyclerView.ViewHolder>
@JvmOverloads
constructor(differ: DiffUtil.ItemCallback<D> = DiffCallback()) : ListAdapter<D, VH>(differ) {

    var listClickListener: BaseListClickListener<D>? = null

    fun update(list: List<D>) {
        this.submitList(ArrayList(list))
    }

    private class DiffCallback<D : DiffComparable> : DiffUtil.ItemCallback<D>() {

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: D, newItem: D): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: D, newItem: D): Boolean =
            oldItem.getItemId() == newItem.getItemId()
    }

    interface BaseListClickListener<D> {

        fun onListClick(view: View, item: D, position: Int)
    }
}

interface DiffComparable {

    fun getItemId(): Int
}