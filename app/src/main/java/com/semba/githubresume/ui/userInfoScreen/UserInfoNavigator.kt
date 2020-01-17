package com.semba.githubresume.ui.userInfoScreen

import com.semba.githubresume.base.BaseNavigator
import com.semba.githubresume.data.GitRepo

/**
 * Created by SeMbA on 2019-12-07.
 */
interface UserInfoNavigator: BaseNavigator {

    /**
     * Fires with any change in repos livedata of ViewModel class.
     */
    fun updateRepos(newRepos: ArrayList<GitRepo>)

    /**
     * Opens an html path in external browser.
     */
    fun openWebPage(pagePath: String?)
}