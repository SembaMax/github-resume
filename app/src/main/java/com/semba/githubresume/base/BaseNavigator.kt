package com.semba.githubresume.base

/**
 * Created by SeMbA on 2019-12-07.
 */
interface BaseNavigator {

    /**
     * Switch between loading mode and display data mode
     */
    fun toggleLoading(isLoading: Boolean)

    /**
     * pass a error message to the activity to display it on the user interface
     */
    fun showErrorMessage(message: String?)
}