package com.semba.githubresume.di.modules

import com.semba.githubresume.api.ApiService
import com.semba.githubresume.data.dataSources.GithubDataSource
import com.semba.githubresume.data.repositories.GithubRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by SeMbA on 2019-12-08.
 */
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideGithubDataSource(apiService: ApiService): GithubDataSource
    {
        return GithubDataSource(apiService)
    }

    @Singleton
    @Provides
    fun provideGithubRepository(dataSource: GithubDataSource): GithubRepository
    {
        return GithubRepository(dataSource)
    }
}