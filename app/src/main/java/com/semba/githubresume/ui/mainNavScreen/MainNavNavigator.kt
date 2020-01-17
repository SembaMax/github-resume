package com.semba.githubresume.ui.mainNavScreen

import com.semba.githubresume.base.BaseNavigator

/**
 * Created by SeMbA on 2019-12-07.
 */
interface MainNavNavigator: BaseNavigator {
    fun switchFragmentOptions(which: MainFragments)
}

enum class MainFragments
{
    Start,
    UserInfo,
}