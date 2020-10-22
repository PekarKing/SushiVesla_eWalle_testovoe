package com.me.testapplication.ui.home.presenters

import android.util.Log
import com.me.testapplication.R
import com.me.testapplication.di.DIManager
import com.me.testapplication.di.services.user.models.ServicesInfo
import com.me.testapplication.di.services.user.models.User
import com.me.testapplication.ui.base.adapters.*
import com.me.testapplication.ui.home.activities.HomeView
import com.me.testapplication.ui.home.interactors.HomeInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject
import kotlin.properties.Delegates

class HomePresenter : MvpPresenter<HomeView>() {

    @Inject
    lateinit var interactor: HomeInteractor

    private var user: User? = null

    private var menuItem: DefaultModel by Delegates.observable(DefaultModel()) { _, _, newValue ->
        viewState.onUpdateMenu(newValue.items)
    }

    private val menuArray = arrayListOf(
        MenuItemModel("Home", true),
        MenuItemModel("Profile", false),
        MenuItemModel("Accounts", false),
        MenuItemModel("Transactions", false),
        MenuItemModel("Stats", false),
        MenuItemModel("Settings", false),
        MenuItemModel("Help", false)
    )

    init {
        DIManager.getHomeSubcomponent().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        DIManager.removeHomeSubcomponent()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user = interactor.getUser()
    }

    fun updateData() {
        presenterScope.launch {
            try {
                val nameBuilder = StringBuilder()
                user?.name?.let { nameBuilder.append(it) }
                nameBuilder.append(" ")
                user?.surname?.let { nameBuilder.append(it) }

                val locationBuilder = StringBuilder()
                user?.city?.cityName?.let { locationBuilder.append(it) }
                locationBuilder.append(", ")
                user?.city?.countryName?.let { locationBuilder.append(it) }

                viewState.onUpdateUser(
                    nameBuilder.toString(),
                    user?.avatar ?: " ",
                    locationBuilder.toString()
                )

                val balanceInfo = withContext(Dispatchers.IO) {
                    interactor.getBalanceInfo(user?.id!!)
                }

                val services = withContext(Dispatchers.IO) {
                    interactor.getAvailableServices()
                }

                var friendsDefaultModel = DefaultModel()
                val friendsModelsList = ArrayList<BaseModel>()
                friendsModelsList.add(FriendPlusButtonListModel)
                user?.friends?.forEach { friendsModelsList.add(FriendListModel(it)) }
                friendsDefaultModel = friendsDefaultModel.copy(items = friendsModelsList)

                var servicesDefaultModel = DefaultModel()
                val servicesModelList = ArrayList<BaseModel>()
                services.forEach { servicesModelList.add(ServiceListModel(it)) }
                servicesModelList.add(
                    ServiceListModel(
                        ServicesInfo(
                            "More\nOptions",
                            R.drawable.ic_more_options
                        )
                    )
                )
                servicesDefaultModel = servicesDefaultModel.copy(items = servicesModelList)

                var homeDefaultModel = DefaultModel()
                val homeModelsList = ArrayList<BaseModel>()
                homeModelsList.addAll(
                    listOf(
                        BalanceItemModel(balanceInfo),
                        SendMoneyItemModel(friendsDefaultModel),
                        ServicesItemModel(servicesDefaultModel)
                    )
                )
                homeDefaultModel = homeDefaultModel.copy(items = homeModelsList)
                viewState.onUpdateList(homeDefaultModel.items)
            } catch (e: Exception) {
                Log.e(TAG, e.message, e)
            }
        }
    }

    fun logout() {
        interactor.logout()
        viewState.successLogout()
    }

    fun updateMenu(position: Int? = null) {
        menuArray.forEach { it.isSelected = false }
        menuArray[position ?: 0].isSelected = true
        menuItem = menuItem.copy(items = menuArray)
    }

    companion object {

        private const val TAG = "HomePresenter"
    }
}