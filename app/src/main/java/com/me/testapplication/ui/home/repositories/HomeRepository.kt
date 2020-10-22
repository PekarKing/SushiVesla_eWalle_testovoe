package com.me.testapplication.ui.home.repositories

import com.me.testapplication.R
import com.me.testapplication.di.services.TimeService
import com.me.testapplication.di.services.api.ApiService
import com.me.testapplication.di.services.api.models.BalanceResponse
import com.me.testapplication.di.services.api.models.ServiceResponse
import com.me.testapplication.di.services.database.DatabaseService
import com.me.testapplication.di.services.user.UserManagerService
import com.me.testapplication.di.services.user.models.BalanceInfo
import com.me.testapplication.di.services.user.models.User
import java.util.*

class HomeRepository(
    private val apiService: ApiService,
    private val timeService: TimeService,
    private val databaseService: DatabaseService,
    private val userManagerService: UserManagerService
) : IHomeRepository {

    override fun getUser(): User? = userManagerService.user

    override fun logout() {
        userManagerService.clear()
    }

    override suspend fun getBalance(id: String): BalanceInfo =
        BalanceInfo(/*apiService.getApi().getBalanceInfo(id)*/ BalanceResponse(
            "00000",
            "20,600",
            "ru"
        )
        )

    override suspend fun getAvailableServices(): ArrayList<ServiceResponse> =
        /*apiService.getApi().getServices()*/ arrayListOf(
        ServiceResponse("Send\nMoney", R.drawable.ic_send_money),
        ServiceResponse("Receive\nMoney", R.drawable.ic_receive_money),
        ServiceResponse("Mobile\nPrepaid", R.drawable.ic_mobile_prepaid),
        ServiceResponse("Electricity\nBill", R.drawable.ic_electricity_bill),
        ServiceResponse("Cashback\nOffer", R.drawable.ic_cashback_offer),
        ServiceResponse("Movie\nTickets", R.drawable.ic_movie_tickets),
        ServiceResponse("Flight\nTickets", R.drawable.ic_flight_tickets)
    )
}