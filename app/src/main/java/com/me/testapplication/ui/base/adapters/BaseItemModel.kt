package com.me.testapplication.ui.base.adapters

import androidx.annotation.LayoutRes
import com.me.testapplication.R
import com.me.testapplication.di.services.user.models.BalanceInfo
import com.me.testapplication.di.services.user.models.FriendInfo
import com.me.testapplication.di.services.user.models.ServicesInfo

data class DefaultModel(
    val items: List<BaseModel> = listOf()
)

sealed class BaseModel : DiffComparable, Comparable<BaseModel> {

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun compareTo(other: BaseModel): Int = getItemId() - other.getItemId()

    override fun getItemId(): Int = 0
}

data class MenuItemModel(
    val title: String,
    var isSelected: Boolean,
) : BaseModel() {

    override fun getLayoutId(): Int = R.layout.rv_item_menu
}

data class BalanceItemModel(
    val balanceInfo: BalanceInfo
) : BaseModel() {

    override fun getLayoutId(): Int = R.layout.rv_item_account_overview
}

data class SendMoneyItemModel(
    val friendsDefaultModel: DefaultModel
) : BaseModel() {

    override fun getLayoutId(): Int = R.layout.rv_item_send_money

    override fun getItemId(): Int = 1
}

data class ServicesItemModel(
    val servicesDefaultModel: DefaultModel
) : BaseModel() {

    override fun getLayoutId(): Int = R.layout.rv_item_services

    override fun getItemId(): Int = 2
}

data class FriendListModel(
    val friend: FriendInfo
) : BaseModel() {

    override fun getLayoutId(): Int = R.layout.rv_content_item_friend

    override fun getItemId(): Int = 1
}

object FriendPlusButtonListModel : BaseModel() {

    override fun getLayoutId(): Int = R.layout.rv_content_item_plus_friend_button
}

data class ServiceListModel(
    val service: ServicesInfo
) : BaseModel() {

    override fun getLayoutId(): Int = R.layout.rv_content_item_service
}