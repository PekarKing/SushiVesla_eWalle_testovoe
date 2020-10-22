package com.me.testapplication.ui.hello.interactors

import com.me.testapplication.di.services.api.models.UserResponse
import com.me.testapplication.ui.hello.repositories.IHelloRepository
import java.text.SimpleDateFormat
import java.util.*

class HelloInteractor(private val repository: IHelloRepository) {

    fun getDate(): String {
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("MMM.dd.yyyy | EEEE", Locale.ENGLISH)
        return formatter.format(calendar.time)
    }

    fun isUserAlreadyAuth() = repository.isUserAlreadyAuth()

    fun getUserPhone() = repository.getUserPhone()

    fun saveUser(userResponse: UserResponse) {
        repository.saveUser(userResponse)
    }

    fun saveUserPhone(phone: String) {
        repository.saveUserPhone(phone)
    }

    fun saveUserAuth() {
        repository.saveUserAuth()
    }

    suspend fun getWeather() = repository.getWeather("cityId")

    suspend fun loadUser(phone: String) = repository.loadUser(phone)
}