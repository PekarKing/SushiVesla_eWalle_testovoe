package com.me.testapplication.ui.hello.presenters

import android.util.Log
import com.me.testapplication.di.DIManager
import com.me.testapplication.ui.hello.activities.HelloView
import com.me.testapplication.ui.hello.interactors.HelloInteractor
import com.me.testapplication.ui.hello.models.WeatherInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class HelloPresenter : MvpPresenter<HelloView>() {

    @Inject
    lateinit var interactor: HelloInteractor

    init {
        DIManager.getHelloSubcomponent().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        DIManager.removeHelloSubcomponent()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (interactor.isUserAlreadyAuth()) {
            loadAndSaveUser(interactor.getUserPhone())
        }
    }

    fun getWeatherInfo() {
        presenterScope.launch {
            try {
                viewState.onShowWeather(
                    withContext(Dispatchers.IO) {
                        interactor.getWeather()
                    }
                )
            } catch (e: Exception) {
                Log.e(TAG, e.message, e)
                viewState.onShowWeather(WeatherInfo())
            }
        }
    }

    fun getDateInfo() {
        viewState.onShowDate(interactor.getDate())
    }

    fun loadAndSaveUser(phone: String) {
        presenterScope.launch {
            try {
                val userResponse =
                    withContext(Dispatchers.IO) { interactor.loadUser(phone) }
                interactor.saveUser(userResponse)
                interactor.saveUserAuth()
                interactor.saveUserPhone(phone)
                viewState.onOpenNextScreen()
            } catch (e: Exception) {
                Log.e(TAG, e.message, e)
            }
        }
    }

    companion object {
        private const val TAG = "HelloPresenter"
    }

}