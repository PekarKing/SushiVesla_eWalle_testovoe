package com.me.testapplication.ui.hello.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.me.testapplication.R
import com.me.testapplication.ui.base.activities.BaseActivity
import com.me.testapplication.ui.base.views.BaseView
import com.me.testapplication.ui.hello.models.WeatherInfo
import com.me.testapplication.ui.hello.presenters.HelloPresenter
import com.me.testapplication.ui.home.activities.HomeActivity
import kotlinx.android.synthetic.main.activity_hello.*
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.util.*

@StateStrategyType(OneExecutionStateStrategy::class)
interface HelloView : BaseView {

    fun onShowWeather(weatherInfo: WeatherInfo)
    fun onShowDate(formattedDate: String)
    fun onOpenNextScreen()
}

class HelloActivity : BaseActivity(), HelloView {

    private val presenter: HelloPresenter by moxyPresenter { HelloPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        rv_hello_sign_in.setOnClickListener {
            presenter.loadAndSaveUser("+1 234 567 890")
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getWeatherInfo()
        presenter.getDateInfo()
    }

    override fun onShowWeather(weatherInfo: WeatherInfo) {
        weatherInfo.temperature?.let {
            tv_hello_temp.text =
                String.format(Locale.getDefault(), resources.getString(R.string.degrees_sign), it)
        }
    }

    override fun onShowDate(formattedDate: String) {
        tv_hello_date.text = formattedDate
    }

    override fun onOpenNextScreen() {
        startActivity(HomeActivity.newIntent(this))
        finish()
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, HelloActivity::class.java)
    }
}