package com.me.testapplication.ui.home.interactors

import com.me.testapplication.di.services.user.models.ServicesInfo
import com.me.testapplication.ui.home.repositories.IHomeRepository

class HomeInteractor(private val repository: IHomeRepository) {

    fun getUser() = repository.getUser()

    fun logout() = repository.logout()

    suspend fun getBalanceInfo(id: String) = repository.getBalance(id)

    suspend fun getAvailableServices(): ArrayList<ServicesInfo> {
        val services = ArrayList<ServicesInfo>()
        repository.getAvailableServices().forEach { services.add(ServicesInfo(it)) }
        return services
    }
}