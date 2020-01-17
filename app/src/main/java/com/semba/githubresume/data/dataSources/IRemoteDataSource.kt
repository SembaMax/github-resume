package com.semba.githubresume.data.dataSources

import com.semba.githubresume.data.GitRepo
import com.semba.githubresume.data.UserInfo
import io.reactivex.Observable

/**
 * Created by SeMbA on 2019-12-07.
 */
interface IRemoteDataSource {

    fun getUserInfo(userName: String): Observable<UserInfo>
    fun getRepos(userName: String): Observable<ArrayList<GitRepo>>
}