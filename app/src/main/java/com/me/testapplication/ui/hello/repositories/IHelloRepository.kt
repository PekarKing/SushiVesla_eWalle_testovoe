package com.me.testapplication.ui.hello.repositories

import com.me.testapplication.di.services.api.models.UserResponse
import com.me.testapplication.ui.hello.models.WeatherInfo

interface IHelloRepository {

    fun isUserAlreadyAuth(): Boolean

    fun getUserPhone(): String

    fun saveUser(userResponse: UserResponse)

    fun saveUserPhone(phone: String)

    fun saveUserAuth()

    suspend fun getWeather(cityId: String): WeatherInfo

    suspend fun loadUser(phone: String): UserResponse
}