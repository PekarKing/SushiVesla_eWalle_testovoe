package com.me.testapplication.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.me.testapplication.R
import com.me.testapplication.ui.base.adapters.BaseModel
import com.me.testapplication.ui.base.adapters.BaseRVAdapter
import com.me.testapplication.ui.base.adapters.ServiceListModel

@Suppress("unused")
class ServicesRVAdapter(private val context: Context) :
    BaseRVAdapter<BaseModel, ServicesRVAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder =
        ServiceViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_content_item_service, parent, false)
        )

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ServiceViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val tvTitle = v.findViewById(R.id.tv_service_item_titles) as TextView
        private val ivIcon = v.findViewById(R.id.iv_service_item_icon) as ImageView

        fun bind(position: Int) {
            val serviceInfo = (getItem(position) as ServiceListModel).service
            tvTitle.text = serviceInfo.title
            ivIcon.setImageDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    serviceInfo.iconLink,
                    context.theme
                )
            )
        }
    }
}