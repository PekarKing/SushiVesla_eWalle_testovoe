package com.me.testapplication.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.me.testapplication.R
import com.me.testapplication.ui.base.adapters.*

class HomeCategoriesRVAdapter(private val context: Context) :
    BaseRVAdapter<BaseModel, RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getLayoutId()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.rv_item_account_overview -> BalanceViewHolder(view)
            R.layout.rv_item_send_money -> SendMoneyViewHolder(view)
            R.layout.rv_item_services -> ServicesViewHolder(view)
            else -> throw IllegalStateException("Unknown layout id")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BalanceViewHolder -> holder.bind(position)
            is SendMoneyViewHolder -> holder.bind(position)
            is ServicesViewHolder -> holder.bind(position)
        }
    }

    inner class BalanceViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val tvBalance = v.findViewById(R.id.tv_home_balance) as TextView

        init {
            tvBalance.setOnClickListener {
                listClickListener?.onListClick(it, getItem(adapterPosition), adapterPosition)
            }
        }

        fun bind(position: Int) {
            val balanceInfo = (getItem(position) as BalanceItemModel).balanceInfo
            tvBalance.text = balanceInfo.balance
        }
    }

    inner class SendMoneyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val rvFriends = v.findViewById(R.id.rv_send_money_friends_list) as RecyclerView
        private val ivGrid = v.findViewById(R.id.iv_make_rv_grid) as ImageView

        private lateinit var rvAdapter: FriendsRVAdapter

        private var isGrid = false

        init {
            ivGrid.setOnClickListener {
                changeRVtoGrid()
            }
        }

        fun bind(position: Int) {
            val friendsModel = (getItem(position) as SendMoneyItemModel).friendsDefaultModel
            rvAdapter = FriendsRVAdapter(context)
            rvFriends.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvFriends.adapter = rvAdapter
            rvAdapter.update(friendsModel.items)
        }

        private fun changeRVtoGrid() {
            rvFriends.layoutManager = if (!isGrid) {
                isGrid = true
                GridLayoutManager(context, 3)
            } else {
                isGrid = false
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    inner class ServicesViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val rvServices = v.findViewById(R.id.rv_services_list) as RecyclerView

        private lateinit var rvAdapter: ServicesRVAdapter

        fun bind(position: Int) {
            val servicesModel = (getItem(position) as ServicesItemModel).servicesDefaultModel
            rvAdapter = ServicesRVAdapter(context)
            rvServices.layoutManager = GridLayoutManager(context, 4)
            rvServices.adapter = rvAdapter
            rvAdapter.update(servicesModel.items)
        }
    }
}