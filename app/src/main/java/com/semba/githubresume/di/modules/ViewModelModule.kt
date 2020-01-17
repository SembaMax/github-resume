package com.semba.githubresume.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semba.githubresume.di.keys.ViewModelKey
import com.semba.githubresume.ui.mainNavScreen.MainNavViewModel
import com.semba.githubresume.ui.startScreen.StartViewModel
import com.semba.githubresume.ui.userInfoScreen.UserInfoViewModel
import com.semba.githubresume.utils.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by SeMbA on 2019-12-07.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainNavViewModel::class)
    abstract fun bindMainNavViewModel(viewModel: MainNavViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    abstract fun bindStartViewModel(viewModel: StartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserInfoViewModel::class)
    abstract fun bindUserInfoViewModel(viewModel: UserInfoViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}