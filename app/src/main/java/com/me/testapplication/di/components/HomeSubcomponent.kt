package com.me.testapplication.di.components

import com.me.testapplication.di.modules.HomeModule
import com.me.testapplication.di.scopes.HomeScope
import com.me.testapplication.ui.home.presenters.HomePresenter
import dagger.Subcomponent

@HomeScope
@Subcomponent(modules = [HomeModule::class])
interface HomeSubcomponent {

    fun inject(presenter: HomePresenter)
}