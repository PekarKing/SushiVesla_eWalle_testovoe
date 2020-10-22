package com.me.testapplication.ui.base.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.me.testapplication.R

class NavigationMenuAdapter(private val context: Context) :
    BaseRVAdapter<BaseModel, NavigationMenuAdapter.MenuItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder =
        MenuItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_menu, parent, false)
        )

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class MenuItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val llParent = v.findViewById(R.id.ll_item_menu) as LinearLayout
        private val tvTitle = v.findViewById(R.id.tv_item_menu_title) as TextView
        private val vIndicator = v.findViewById(R.id.v_item_menu_indicator) as View

        init {
            llParent.setOnClickListener {
                listClickListener?.onListClick(it, getItem(adapterPosition), adapterPosition)
            }
        }

        fun bind(position: Int) {
            val item = (getItem(position) as MenuItemModel)
            tvTitle.text = item.title
            val typeFace = if (item.isSelected) ResourcesCompat.getFont(
                context,
                R.font.avenir_next_cyr_700
            ) else ResourcesCompat.getFont(context, R.font.avenir_next_cyr_500)
            tvTitle.typeface = typeFace
            vIndicator.visibility = if (item.isSelected) View.VISIBLE else View.INVISIBLE
        }
    }
}