package com.semba.githubresume.api

/**
 * Created by SeMbA on 2019-12-08.
 */
interface ApiCallback {

    fun onNext(parameter: Any)
    fun onComplete()
    fun onError(message: String)
}