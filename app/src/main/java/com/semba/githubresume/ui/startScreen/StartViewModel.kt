package com.semba.githubresume.ui.startScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.semba.githubresume.api.ApiCallback
import com.semba.githubresume.base.BaseViewModel
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
class StartViewModel @Inject constructor(private val repository: GithubRepository,
                                         private val mSchedulerProvider: ISchedulerProvider,
                                         private val logger: Logger
): BaseViewModel<StartNavigator>(), ApiCallback {

    var userName: String = ""
    var userInfo = MutableLiveData<UserInfo>()

    private val profileObserver = Observer<UserInfo> {
        mNavigator?.get()?.navigateToUserInfo(it)
    }

    init {
        userInfo.observeForever(profileObserver)
    }

    fun onGenerateClicked()
    {
        executeLogic()
    }

    override fun executeLogic() {
        if (validate())
        {
            toggleLoading(true)
            fetchUserProfile(userName)
        }
    }

    fun validate(): Boolean
    {
        if (userName.isEmpty())
        {
            mNavigator?.get()?.showErrorMessage("Please Enter UserName")
            return false
        }

        return true
    }

    /**
     * Execute api call for getting Repos data.
     */
    fun fetchUserProfile(parameter: String) {
        mCompositeDisposable.add(repository.callGetUserApi(parameter)
            .observeOn(mSchedulerProvider.ui())
            .subscribeOn(mSchedulerProvider.io())
            .subscribeWith(object : DisposableObserver<UserInfo>(){
                override fun onComplete() {
                    this@StartViewModel.onComplete()
                    logger.d("Observer: Completed")
                }

                override fun onNext(t: UserInfo) {
                    this@StartViewModel.onNext(t)
                    logger.d(t.toString())
                }

                override fun onError(e: Throwable) {
                    this@StartViewModel.onError(e.message ?: Constants.ERROR)
                    logger.e(e.toString())
                }
            }))
    }

    override fun onNext(parameter: Any) {
        if (parameter is UserInfo)
            userInfo.value = parameter
    }

    override fun onComplete() {
        toggleLoading(false)
    }

    override fun onError(message: String) {
        toggleLoading(false)
        mNavigator?.get()?.showErrorMessage(message)
    }

    override fun onCleared() {
        userInfo.removeObserver(profileObserver)
        super.onCleared()
    }

}