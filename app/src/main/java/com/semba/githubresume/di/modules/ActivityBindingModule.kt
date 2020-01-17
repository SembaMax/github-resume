package com.semba.githubresume.di.modules

import com.semba.githubresume.ui.mainNavScreen.MainNavActivity
import com.semba.githubresume.ui.startScreen.StartFragment
import com.semba.githubresume.ui.userInfoScreen.UserInfoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by SeMbA on 2019-12-07.
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeMainNavActivity(): MainNavActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeUserInfoFragment(): UserInfoFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeStartFragment(): StartFragment
}