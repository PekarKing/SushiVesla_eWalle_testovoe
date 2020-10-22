package com.me.testapplication.ui.home.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.me.testapplication.R
import com.me.testapplication.ui.base.adapters.BaseModel
import com.me.testapplication.ui.base.adapters.BaseRVAdapter
import com.me.testapplication.ui.base.adapters.FriendListModel
import com.me.testapplication.utils.transformers.CircleTransform
import com.squareup.picasso.Picasso

class FriendsRVAdapter(private val context: Context?) :
    BaseRVAdapter<BaseModel, RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getLayoutId()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.rv_content_item_plus_friend_button -> FriendPlusButtonViewHolder(view)
            R.layout.rv_content_item_friend -> FriendViewHolder(view)
            else -> throw IllegalStateException("Unknown layout id")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FriendPlusButtonViewHolder -> holder.bind(position)
            is FriendViewHolder -> holder.bind(position)
        }
    }

    inner class FriendPlusButtonViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val fab = v.findViewById(R.id.fab_friend_item_add) as FloatingActionButton

        fun bind(position: Int) {}
    }

    inner class FriendViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val tvName = v.findViewById(R.id.tv_friend_item_name) as TextView
        private val ivAvatar = v.findViewById(R.id.iv_friend_item_avatar) as ImageView

        private val Int.px: Float
            get() = (this * Resources.getSystem().displayMetrics.density)

        fun bind(position: Int) {


            val friend = (getItem(position) as FriendListModel).friend
            tvName.text = friend.name

            Picasso.get()
                .load(friend.avatar)
                .placeholder(R.drawable.ic_avatar_placeholder)
                .transform(CircleTransform(2.px, R.color.blue_3a4))
                .into(ivAvatar)
        }
    }
}