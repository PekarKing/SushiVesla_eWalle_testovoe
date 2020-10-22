package com.me.testapplication.di.modules

import com.me.testapplication.di.scopes.HelloScope
import com.me.testapplication.di.services.api.ApiService
import com.me.testapplication.di.services.database.DatabaseService
import com.me.testapplication.di.services.TimeService
import com.me.testapplication.di.services.user.UserManagerService
import com.me.testapplication.ui.hello.interactors.HelloInteractor
import com.me.testapplication.ui.hello.repositories.HelloRepository
import com.me.testapplication.ui.hello.repositories.IHelloRepository
import dagger.Module
import dagger.Provides

@Module
object HelloModule {

    @Provides
    @HelloScope
    fun provideRepository(
        apiService: ApiService,
        timeService: TimeService,
        databaseService: DatabaseService,
        userManagerService: UserManagerService
    ): IHelloRepository =
        HelloRepository(apiService, timeService, databaseService, userManagerService)

    @Provides
    @HelloScope
    fun provideInteractor(repository: IHelloRepository) = HelloInteractor(repository)
}