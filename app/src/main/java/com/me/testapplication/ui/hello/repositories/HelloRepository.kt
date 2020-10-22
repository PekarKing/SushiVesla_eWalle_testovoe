package com.me.testapplication.ui.hello.repositories

import com.me.testapplication.di.services.TimeService
import com.me.testapplication.di.services.api.ApiService
import com.me.testapplication.di.services.api.models.CityInfoResponse
import com.me.testapplication.di.services.api.models.UserResponse
import com.me.testapplication.di.services.database.DatabaseService
import com.me.testapplication.di.services.user.UserManagerService
import com.me.testapplication.di.services.user.models.FriendInfo
import com.me.testapplication.ui.hello.models.WeatherInfo
import com.me.testapplication.ui.hello.models.WeatherState

class HelloRepository(
    private val apiService: ApiService,
    private val timeService: TimeService,
    private val databaseService: DatabaseService,
    private val userManagerService: UserManagerService
) : IHelloRepository {

    override fun isUserAlreadyAuth(): Boolean = userManagerService.isAuth

    override fun getUserPhone(): String = userManagerService.phone

    override fun saveUser(userResponse: UserResponse) {
        userManagerService.saveUserInfo(userResponse)
    }

    override fun saveUserPhone(phone: String) {
        userManagerService.phone = phone
    }

    override fun saveUserAuth() {
        userManagerService.isAuth = true
    }

    override suspend fun getWeather(cityId: String): WeatherInfo =
        /*apiService.getApi().getWeather(cityId)*/ WeatherInfo("18", WeatherState.SUNNY)

    override suspend fun loadUser(phone: String): UserResponse =
        /*apiService.getApi().getUser(phone)*/ UserResponse(
        "0",
        "Keanu",
        "Reeves",
        "https://pyxis.nymag.com/v1/imgs/654/1f1/08de774c11d89cb3f4ecf600a33e9c8283-24-keanu-reeves.rsquare.w1200.jpg",
        CityInfoResponse("0", "Toronto", "Canada"),
        arrayListOf(
            FriendInfo(
                "1",
                "Ryan",
                "Gosling",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Ryan_Gosling_in_2018.jpg/330px-Ryan_Gosling_in_2018.jpg"
            ),
            FriendInfo(
                "2",
                "Anne",
                "Hathaway",
                "https://m.media-amazon.com/images/M/MV5BMTRhNzQ3NGMtZmQ1Mi00ZTViLTk3OTgtOTk0YzE2YTgwMmFjXkEyXkFqcGdeQXVyNzg5MzIyOA@@._V1_.jpg"
            ),
            FriendInfo(
                "3",
                "James",
                "Carrey",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8b/Jim_Carrey_2008.jpg/330px-Jim_Carrey_2008.jpg"
            ),
            FriendInfo(
                "4",
                "Emilia",
                "Clarke",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Emilia_Clarke_by_Gage_Skidmore_2_%28cropped%29.jpg/330px-Emilia_Clarke_by_Gage_Skidmore_2_%28cropped%29.jpg"
            )
        )
    )
}