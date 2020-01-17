package com.semba.githubresume.ui.startScreen

import com.semba.githubresume.base.BaseNavigator
import com.semba.githubresume.data.UserInfo

/**
 * Created by SeMbA on 2019-12-07.
 */
interface StartNavigator: BaseNavigator {

    fun navigateToUserInfo(userInfo: UserInfo)
}