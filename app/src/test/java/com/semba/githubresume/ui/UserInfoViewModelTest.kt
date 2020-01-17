package com.semba.githubresume.ui

import com.semba.githubresume.api.ApiService
import com.semba.githubresume.data.GitRepo
import com.semba.githubresume.data.UserInfo
import com.semba.githubresume.data.dataSources.GithubDataSource
import com.semba.githubresume.data.repositories.GithubRepository
import com.semba.githubresume.ui.userInfoScreen.UserInfoNavigator
import com.semba.githubresume.ui.userInfoScreen.UserInfoViewModel
import com.semba.githubresume.utils.InstantTaskExecutorRule
import com.semba.githubresume.utils.Logger
import com.semba.githubresume.utils.TestSchedulerProvider
import io.reactivex.Observable
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

/**
 * Created by SeMbA on 2019-12-08.
 */
@ExtendWith(InstantTaskExecutorRule::class)
class UserInfoViewModelTest {

    lateinit var navigator: UserInfoNavigator
    lateinit var logger: Logger
    lateinit var scheduler: TestSchedulerProvider

    lateinit var apiService: ApiService
    lateinit var githubDataSource: GithubDataSource
    lateinit var repository: GithubRepository
    private lateinit var viewModel: UserInfoViewModel

    private val name = "Salah"
    private val noOne = "NoOne"
    private val errorMessage = "Error"
    private val correctUserInfo = UserInfo(1,name,"","",name,0,0,0)
    private val wrongUserInfo = UserInfo(1,noOne,"","",noOne,0,0,0)
    private val repo = GitRepo(1,"","","", Date(), Date(),0,0,0,0,"")
    private val response = arrayListOf(repo,repo,repo)

    @BeforeEach
    fun setup()
    {
        navigator = mock(UserInfoNavigator::class.java)
        logger = mock(Logger::class.java)
        apiService = mock(ApiService::class.java, RETURNS_DEEP_STUBS)
        `when`(apiService.getUserRepos(name)).thenReturn(Observable.just(response))
        `when`(apiService.getUserRepos(noOne)).thenReturn(Observable.error(Throwable(errorMessage)))

        githubDataSource = GithubDataSource(apiService)
        repository = GithubRepository(githubDataSource)
        scheduler = TestSchedulerProvider()

        viewModel = UserInfoViewModel(repository, scheduler, logger)
        viewModel.setNavigator(navigator)
    }

    @AfterEach
    fun after()
    {

    }

    @Test
    fun `Execute Logic With Success`()
    {
        val spy = spy(viewModel)
        val order = inOrder(spy)
        spy.userInfo.value = correctUserInfo

        spy.executeLogic()
        order.verify(spy).toggleLoading(true)
        order.verify(spy).fetchRepos(name)
        order.verify(spy).onNext(response)
        order.verify(spy).onComplete()
        order.verify(spy).toggleLoading(false)
    }

    @Test
    fun `Execute Logic With Error`()
    {
        val spy = spy(viewModel)
        val order = inOrder(spy)
        spy.userInfo.value = wrongUserInfo

        spy.executeLogic()
        order.verify(spy).toggleLoading(true)
        order.verify(spy).fetchRepos(noOne)
        order.verify(spy).onError(errorMessage)
        order.verify(spy).toggleLoading(false)
    }

    @Test
    fun `Refresh UI`()
    {
        viewModel.repos.value = response

        verify(navigator).updateRepos(response)
    }
}