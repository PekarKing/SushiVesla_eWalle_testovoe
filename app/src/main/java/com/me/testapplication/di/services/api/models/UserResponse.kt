package com.me.testapplication.di.services.api.models

import com.me.testapplication.di.services.user.models.FriendInfo

data class UserResponse(
    val id: String? = null,
    val name: String? = null,
    val surname: String? = null,
    val avatar: String? = null,
    val city: CityInfoResponse? = null,
    val friends: ArrayList<FriendInfo>? = null
)

data class CityInfoResponse(
    val cityId: String? = null,
    val cityName: String? = null,
    val countryName: String? = null,
)