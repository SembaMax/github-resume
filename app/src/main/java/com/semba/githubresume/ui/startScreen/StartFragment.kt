package com.semba.githubresume.ui.startScreen


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.semba.githubresume.BR
import com.semba.githubresume.R
import com.semba.githubresume.base.BaseFragment
import com.semba.githubresume.data.UserInfo
import com.semba.githubresume.databinding.FragmentStartBinding
import com.semba.githubresume.ui.mainNavScreen.MainFragments
import com.semba.githubresume.ui.mainNavScreen.MainNavActivity
import com.semba.githubresume.ui.mainNavScreen.MainNavNavigator
import com.semba.githubresume.utils.ViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_start.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class StartFragment : BaseFragment<FragmentStartBinding, StartViewModel>(), StartNavigator {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private var logoAnimation: Animation? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_start
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun onStart() {
        super.onStart()

        (activity as MainNavActivity).switchFragmentOptions(MainFragments.Start)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        this.assignViewModel(viewModelFactory, StartViewModel::class.java)
        super.onCreate(savedInstanceState)
        this.mViewModel.setNavigator(this)

        initialize()
    }

    private fun initialize() {
        logoAnimation = AnimationUtils.loadAnimation(activity, R.anim.logo_animation)
    }

    override fun onResume() {
        super.onResume()
        runAnimation(true)
    }

    override fun onPause() {
        super.onPause()
        runAnimation(false)
    }

    private fun runAnimation(animate: Boolean) {
        if (animate) {
            github_logo?.startAnimation(logoAnimation)
            logo_ripples?.startRippleAnimation()
        }
        else
        {
            github_logo?.clearAnimation()
            logo_ripples?.stopRippleAnimation()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun toggleLoading(isLoading: Boolean) {
        progress?.visibility = if (isLoading) View.VISIBLE else View.GONE
        generate_btn?.visibility = if (isLoading) View.GONE else View.VISIBLE
    }

    override fun showErrorMessage(message: String?) {
        (activity as MainNavNavigator?)?.showErrorMessage(message)
    }

    override fun navigateToUserInfo(userInfo: UserInfo) {
        val action = StartFragmentDirections.actionStartFragmentToUserInfoFragment(userInfo)
        findNavController().navigate(action)
    }

}
