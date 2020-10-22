package com.me.testapplication.di.components

import android.content.Context
import com.me.testapplication.di.modules.AppModule
import com.me.testapplication.di.modules.HelloModule
import com.me.testapplication.di.modules.HomeModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    val context: Context

    fun addHelloSubcomponent(module: HelloModule): HelloSubcomponent

    fun addHomeSubcomponent(module: HomeModule): HomeSubcomponent

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}