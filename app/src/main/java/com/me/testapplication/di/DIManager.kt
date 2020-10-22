package com.me.testapplication.di

import com.me.testapplication.di.components.AppComponent
import com.me.testapplication.di.components.HelloSubcomponent
import com.me.testapplication.di.components.HomeSubcomponent
import com.me.testapplication.di.modules.HelloModule
import com.me.testapplication.di.modules.HomeModule

object DIManager {

    lateinit var appComponent: AppComponent
    private var helloSubcomponent: HelloSubcomponent? = null
    private var homeSubcomponent: HomeSubcomponent? = null

    fun getHelloSubcomponent(): HelloSubcomponent =
        helloSubcomponent ?: appComponent.addHelloSubcomponent(HelloModule)

    fun removeHelloSubcomponent() {
        helloSubcomponent = null
    }

    fun getHomeSubcomponent(): HomeSubcomponent =
        homeSubcomponent ?: appComponent.addHomeSubcomponent(HomeModule)

    fun removeHomeSubcomponent() {
        homeSubcomponent = null
    }
}