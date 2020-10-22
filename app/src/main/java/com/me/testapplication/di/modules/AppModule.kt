package com.me.testapplication.di.modules

import com.me.testapplication.di.services.api.ApiService
import com.me.testapplication.di.services.database.DatabaseService
import com.me.testapplication.di.services.TimeService
import com.me.testapplication.di.services.user.UserManagerService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideUserManagerService() = UserManagerService()

    @Provides
    @Singleton
    fun provideApiService(moshi: Moshi) = ApiService(moshi)

    @Provides
    @Singleton
    fun provideDatabaseService() = DatabaseService()

    @Provides
    @Singleton
    fun provideDayInfoService() = TimeService()
    
}