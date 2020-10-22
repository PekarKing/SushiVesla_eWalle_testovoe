package com.me.testapplication

import android.app.Application
import com.me.testapplication.di.DIManager
import com.me.testapplication.di.components.DaggerAppComponent

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DIManager.appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}