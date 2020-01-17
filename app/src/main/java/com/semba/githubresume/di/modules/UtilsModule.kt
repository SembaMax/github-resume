package com.semba.githubresume.di.modules

import com.semba.githubresume.utils.Logger
import com.semba.githubresume.utils.rx.AppSchedulerProvider
import com.semba.githubresume.utils.rx.ISchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by SeMbA on 2019-12-07.
 */
@Module
class UtilsModule {

    @Singleton
    @Provides
    fun provideRxScheduler() : ISchedulerProvider
    {
        return AppSchedulerProvider()
    }

    @Singleton
    @Provides
    fun provideLogger() : Logger
    {
        return Logger()
    }
}