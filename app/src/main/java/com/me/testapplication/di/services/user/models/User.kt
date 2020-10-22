package com.me.testapplication.di.services.user.models

import com.me.testapplication.di.services.api.models.CityInfoResponse
import com.me.testapplication.di.services.api.models.UserResponse

data class User(
    var id: String? = null,
    var name: String? = null,
    var surname: String? = null,
    var avatar: String? = null,
    var city: CityInfo? = null,
    var friends: ArrayList<FriendInfo>? = null
) {

    constructor(userResponse: UserResponse) : this() {
        this.id = userResponse.id
        this.name = userResponse.name
        this.surname = userResponse.surname
        this.avatar = userResponse.avatar
        this.city = CityInfo(userResponse.city)
        this.friends = userResponse.friends
    }
}

data class CityInfo(
    val cityId: String? = null,
    val cityName: String? = null,
    val countryName: String? = null
) {

    constructor(cityInfoResponse: CityInfoResponse?) : this(
        cityInfoResponse?.cityId,
        cityInfoResponse?.cityName,
        cityInfoResponse?.countryName
    )
}

data class FriendInfo(
    val id: String? = null,
    val name: String? = null,
    val surname: String? = null,
    val avatar: String? = null
)