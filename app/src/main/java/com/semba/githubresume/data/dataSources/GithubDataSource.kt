package com.semba.githubresume.data.dataSources

import com.semba.githubresume.api.ApiService
import com.semba.githubresume.data.GitRepo
import com.semba.githubresume.data.UserInfo
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by SeMbA on 2019-12-08.
 */
class GithubDataSource @Inject constructor(private val apiService: ApiService): IRemoteDataSource {


    override fun getUserInfo(userName: String): Observable<UserInfo> {
        return apiService.getUserInfo(userName)
    }

    override fun getRepos(userName: String): Observable<ArrayList<GitRepo>> {
        return apiService.getUserRepos(userName)
    }


}