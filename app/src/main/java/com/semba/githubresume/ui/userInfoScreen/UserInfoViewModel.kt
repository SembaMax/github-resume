package com.semba.githubresume.ui.userInfoScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.semba.githubresume.api.ApiCallback
import com.semba.githubresume.base.BaseViewModel
import com.semba.githubresume.data.GitRepo
import com.semba.githubresume.data.UserInfo
import com.semba.githubresume.data.repositories.GithubRepository
import com.semba.githubresume.utils.Constants
import com.semba.githubresume.utils.Logger
import com.semba.githubresume.utils.rx.ISchedulerProvider
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject


/**
 * Created by SeMbA on 2019-12-07.
 */
class UserInfoViewModel @Inject constructor(private val repository: GithubRepository,
                                            private val mSchedulerProvider: ISchedulerProvider,
                                            private val logger: Logger): BaseViewModel<UserInfoNavigator>(), ApiCallback {

    var userInfo = MutableLiveData<UserInfo>()
    var repos = MutableLiveData<ArrayList<GitRepo>>()

    /**
     * Observe the changes of the repos, in order to notify the recyclerView with the new data.
     */
    private val reposObserver = Observer<ArrayList<GitRepo>> {
        mNavigator?.get()?.updateRepos(it)
    }

    private val profileObserver = Observer<UserInfo> {
        executeLogic()
    }

    init {
        repos.observeForever(reposObserver)
        userInfo.observeForever(profileObserver)
    }

    override fun executeLogic() {
        toggleLoading(true)
        fetchRepos(userInfo.value?.username ?: "")
    }

    /**
     * Execute api call for getting Repos data.
     */
    fun fetchRepos(parameter: String) {
        mCompositeDisposable.add(repository.callGetReposApi(parameter)
            .observeOn(mSchedulerProvider.ui())
            .subscribeOn(mSchedulerProvider.io())
            .subscribeWith(object : DisposableObserver<ArrayList<GitRepo>>(){
                override fun onComplete() {
                    this@UserInfoViewModel.onComplete()
                    logger.d("Observer: Completed")
                }

                override fun onNext(t: ArrayList<GitRepo>) {
                    this@UserInfoViewModel.onNext(t)
                    logger.d(t.toString())
                }

                override fun onError(e: Throwable) {
                    this@UserInfoViewModel.onError(e.message ?: Constants.ERROR)
                    logger.e(e.toString())
                }
            }))
    }

    fun onVisitClicked()
    {
        mNavigator?.get()?.openWebPage(userInfo.value?.pagePath)
    }

    override fun onNext(parameter: Any) {
        repos.value = parameter as? ArrayList<GitRepo>
    }

    override fun onComplete() {
        toggleLoading(false)
    }

    override fun onError(message: String) {
        toggleLoading(false)
        mNavigator?.get()?.showErrorMessage(message)
    }

    /**
     * Unsubscribe all our observers.
     */
    override fun onCleared() {
        repos.removeObserver(reposObserver)
        userInfo.removeObserver(profileObserver)
        super.onCleared()
    }
}