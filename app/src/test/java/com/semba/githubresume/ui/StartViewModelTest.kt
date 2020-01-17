package com.semba.githubresume.ui

import com.semba.githubresume.api.ApiService
import com.semba.githubresume.data.UserInfo
import com.semba.githubresume.data.dataSources.GithubDataSource
import com.semba.githubresume.data.repositories.GithubRepository
import com.semba.githubresume.ui.startScreen.StartNavigator
import com.semba.githubresume.ui.startScreen.StartViewModel
import com.semba.githubresume.utils.InstantTaskExecutorRule
import com.semba.githubresume.utils.Logger
import com.semba.githubresume.utils.TestSchedulerProvider
import io.reactivex.Observable
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@ExtendWith(InstantTaskExecutorRule::class)
class StartViewModelTest {

    lateinit var navigator: StartNavigator
    lateinit var logger: Logger
    lateinit var scheduler: TestSchedulerProvider

    lateinit var apiService: ApiService
    lateinit var githubDataSource: GithubDataSource
    lateinit var repository: GithubRepository
    private lateinit var viewModel: StartViewModel

    private val name = "Salah"
    private val noOne = "NoOne"
    private val errorMessage = "Error"
    private val userInfo = UserInfo(1,name,"","",name,0,0,0)

    @BeforeEach
    fun setup()
    {
        navigator = mock(StartNavigator::class.java)
        logger = mock(Logger::class.java)
        apiService = mock(ApiService::class.java, RETURNS_DEEP_STUBS)
        `when`(apiService.getUserInfo(name)).thenReturn(Observable.just(userInfo))
        `when`(apiService.getUserInfo(noOne)).thenReturn(Observable.error(Throwable(errorMessage)))

        githubDataSource = GithubDataSource(apiService)
        repository = GithubRepository(githubDataSource)
        scheduler = TestSchedulerProvider()

        viewModel = StartViewModel(repository, scheduler, logger)
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
        spy.userName = name

        spy.onGenerateClicked()
        order.verify(spy).executeLogic()
        order.verify(spy).toggleLoading(true)
        order.verify(spy).fetchUserProfile(name)
        order.verify(spy).onNext(userInfo)
        order.verify(spy).onComplete()
        order.verify(spy).toggleLoading(false)
    }

    @Test
    fun `Execute Logic With Error`()
    {
        val spy = spy(viewModel)
        val order = inOrder(spy)
        spy.userName = noOne

        spy.onGenerateClicked()
        order.verify(spy).executeLogic()
        order.verify(spy).toggleLoading(true)
        order.verify(spy).fetchUserProfile(noOne)
        order.verify(spy).onError(errorMessage)
        order.verify(spy).toggleLoading(false)
    }

    @Test
    fun `Navigate with the correct data`()
    {
        viewModel.onNext(userInfo)

        verify(navigator).navigateToUserInfo(userInfo)
    }

    @Test
    fun `Validate username`()
    {
        viewModel.userName = name
        val bool = viewModel.validate()
        assertTrue { bool }
    }

    @Test
    fun `Validate empty username`()
    {
        viewModel.userName = ""
        val bool = viewModel.validate()
        assertFalse { bool }
    }
}
