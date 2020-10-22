package com.me.testapplication.di.services.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.me.testapplication.BuildConfig
import com.me.testapplication.di.services.api.models.BalanceResponse
import com.me.testapplication.di.services.api.models.ServiceResponse
import com.me.testapplication.di.services.api.models.UserResponse
import com.me.testapplication.ui.hello.models.WeatherInfo
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class ApiService(private val moshi: Moshi) {

    private var api: Api? = null

    private val client by lazy {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
//            .addInterceptor(CustomInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    when {
                        BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
                        else -> HttpLoggingInterceptor.Level.NONE
                    }
                )
            )
            .build()
    }

    fun getApi(): Api =
        if (api == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            api = retrofit.create(Api::class.java)
            api!!
        } else {
            api!!
        }

    interface Api {

        @GET("$API/weather")
        suspend fun getWeather(@Query("cityId") cityId: String): WeatherInfo

//        suspend fun getWeather(cityId: String): WeatherInfo = WeatherInfo("18", WeatherState.SUNNY)

        @GET("$API/user")
        suspend fun getUser(@Query("phone") phone: String): UserResponse

        /*suspend fun getUser(phone: String): UserResponse = UserResponse(
            "0",
            "Keanu",
            "Reeves",
            "https://pyxis.nymag.com/v1/imgs/654/1f1/08de774c11d89cb3f4ecf600a33e9c8283-24-keanu-reeves.rsquare.w1200.jpg",
            CityInfoResponse("0", "Toronto", "Canada"),
            arrayListOf(
                FriendInfoResponse(
                    "1",
                    "Ryan",
                    "Gosling",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Ryan_Gosling_in_2018.jpg/330px-Ryan_Gosling_in_2018.jpg"
                ),
                FriendInfoResponse(
                    "2",
                    "Anne",
                    "Hathaway",
                    "https://m.media-amazon.com/images/M/MV5BMTRhNzQ3NGMtZmQ1Mi00ZTViLTk3OTgtOTk0YzE2YTgwMmFjXkEyXkFqcGdeQXVyNzg5MzIyOA@@._V1_.jpg"
                ),
                FriendInfoResponse(
                    "3",
                    "James",
                    "Carrey",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8b/Jim_Carrey_2008.jpg/330px-Jim_Carrey_2008.jpg"
                ),
                FriendInfoResponse(
                    "4",
                    "Emilia",
                    "Clarke",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Emilia_Clarke_by_Gage_Skidmore_2_%28cropped%29.jpg/330px-Emilia_Clarke_by_Gage_Skidmore_2_%28cropped%29.jpg"
                )
            )
        )*/

        @GET("$API/balance")
        suspend fun getBalanceInfo(@Query("id") id: String): BalanceResponse

        /*suspend fun getBalanceInfo(id: String): BalanceResponse =
            BalanceResponse("00000", "20,600", "ru")*/

        @GET("$API/services")
        suspend fun getServices(): ArrayList<ServiceResponse>

        /*suspend fun getServices(): ArrayList<ServiceResponse> = arrayListOf(
            ServiceResponse("Send\nMoney", R.drawable.ic_send_money),
            ServiceResponse("Receive\nMoney", R.drawable.ic_receive_money),
            ServiceResponse("Mobile\nPrepaid", R.drawable.ic_mobile_prepaid),
            ServiceResponse("Electricity\nBill", R.drawable.ic_electricity_bill),
            ServiceResponse("Cashback\nOffer", R.drawable.ic_cashback_offer),
            ServiceResponse("Movie\nTickets", R.drawable.ic_movie_tickets),
            ServiceResponse("Flight\nTickets", R.drawable.ic_flight_tickets)
        )*/
    }

    companion object {
        private const val BASE_URL = "https://paste_your_domain"
        private const val API = "/paste_your_api_version"
    }
}