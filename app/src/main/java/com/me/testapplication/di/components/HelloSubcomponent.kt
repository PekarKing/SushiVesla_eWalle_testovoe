package com.me.testapplication.di.components

import com.me.testapplication.di.modules.HelloModule
import com.me.testapplication.di.scopes.HelloScope
import com.me.testapplication.ui.hello.presenters.HelloPresenter
import dagger.Subcomponent

@HelloScope
@Subcomponent(modules = [HelloModule::class])
interface HelloSubcomponent {

    fun inject(presenter: HelloPresenter)
}