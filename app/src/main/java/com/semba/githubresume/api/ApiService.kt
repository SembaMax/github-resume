package com.semba.githubresume.api

import com.semba.githubresume.data.GitRepo
import com.semba.githubresume.data.UserInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by SeMbA on 2019-12-07.
 */
interface ApiService {

    @GET(Config.user)
    fun getUserInfo(@Path("username") username: String): Observable<UserInfo>

    @GET(Config.repos)
    fun getUserRepos(@Path("username") username: String): Observable<ArrayList<GitRepo>>
}