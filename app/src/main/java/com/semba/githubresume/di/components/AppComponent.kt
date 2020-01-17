package com.semba.githubresume.di.components

import android.app.Application
import android.content.Context
import com.semba.githubresume.base.BaseApplication
import com.semba.githubresume.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by SeMbA on 2019-12-07.
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBindingModule::class,
    ViewModelModule::class,
    UtilsModule::class,
    NetworkModule::class,
    DataModule::class
])
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
