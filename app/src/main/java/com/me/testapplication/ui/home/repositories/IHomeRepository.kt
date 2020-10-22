package com.me.testapplication.ui.home.repositories

import com.me.testapplication.di.services.api.models.ServiceResponse
import com.me.testapplication.di.services.user.models.BalanceInfo
import com.me.testapplication.di.services.user.models.User
import java.util.ArrayList

interface IHomeRepository {

    fun getUser(): User?

    fun logout()

    suspend fun getBalance(id: String): BalanceInfo

    suspend fun getAvailableServices(): ArrayList<ServiceResponse>
}