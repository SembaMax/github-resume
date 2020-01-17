package com.semba.githubresume.data.repositories

import com.semba.githubresume.data.GitRepo
import com.semba.githubresume.data.UserInfo
import io.reactivex.Observable

/**
 * Created by SeMbA on 2019-12-08.
 */
interface IRepository {

    fun callGetUserApi(username: String): Observable<UserInfo>
    fun callGetReposApi(username: String): Observable<ArrayList<GitRepo>>
}