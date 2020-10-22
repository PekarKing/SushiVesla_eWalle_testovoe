package com.me.testapplication.di.modules

import com.me.testapplication.di.scopes.HomeScope
import com.me.testapplication.di.services.TimeService
import com.me.testapplication.di.services.api.ApiService
import com.me.testapplication.di.services.database.DatabaseService
import com.me.testapplication.di.services.user.UserManagerService
import com.me.testapplication.ui.home.interactors.HomeInteractor
import com.me.testapplication.ui.home.repositories.HomeRepository
import com.me.testapplication.ui.home.repositories.IHomeRepository
import dagger.Module
import dagger.Provides

@Module
object HomeModule {

    @Provides
    @HomeScope
    fun provideRepository(
        apiService: ApiService,
        timeService: TimeService,
        databaseService: DatabaseService,
        userManagerService: UserManagerService
    ): IHomeRepository =
        HomeRepository(apiService, timeService, databaseService, userManagerService)

    @Provides
    @HomeScope
    fun provideInteractor(repository: IHomeRepository) = HomeInteractor(repository)
}