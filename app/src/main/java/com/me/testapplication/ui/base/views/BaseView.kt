package com.me.testapplication.ui.base.views

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface BaseView : MvpView {

    fun showProgressBar(isShow: Boolean)

    fun showMessage(title: String? = null, message: String)
}