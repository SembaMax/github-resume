package com.semba.githubresume.data.repositories

import com.semba.githubresume.data.GitRepo
import com.semba.githubresume.data.UserInfo
import com.semba.githubresume.data.dataSources.GithubDataSource
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by SeMbA on 2019-12-08.
 */
class GithubRepository @Inject constructor(private val githubDataSource: GithubDataSource) : IRepository {

    override fun callGetUserApi(username: String): Observable<UserInfo>
    {
        return githubDataSource.getUserInfo(username)
    }

    override fun callGetReposApi(username: String): Observable<ArrayList<GitRepo>>
    {
        return githubDataSource.getRepos(username)
    }
}