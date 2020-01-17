package com.semba.githubresume.ui.userInfoScreen


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.semba.githubresume.BR
import com.semba.githubresume.R
import com.semba.githubresume.base.BaseFragment
import com.semba.githubresume.data.GitRepo
import com.semba.githubresume.databinding.FragmentUserInfoBinding
import com.semba.githubresume.ui.mainNavScreen.MainFragments
import com.semba.githubresume.ui.mainNavScreen.MainNavActivity
import com.semba.githubresume.ui.mainNavScreen.MainNavNavigator
import com.semba.githubresume.ui.userInfoScreen.adapters.ReposAdapter
import com.semba.githubresume.utils.MarginItemDecoration
import com.semba.githubresume.utils.ViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_user_info.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class UserInfoFragment : BaseFragment<FragmentUserInfoBinding, UserInfoViewModel>(), UserInfoNavigator {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private var adapter: ReposAdapter? = null
    private val args: UserInfoFragmentArgs by navArgs()

    override fun getLayoutId(): Int {
        return R.layout.fragment_user_info
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun onStart() {
        super.onStart()

        (activity as MainNavActivity).switchFragmentOptions(MainFragments.UserInfo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        this.assignViewModel(viewModelFactory, UserInfoViewModel::class.java)
        super.onCreate(savedInstanceState)
        this.mViewModel.setNavigator(this)
    }

    /**
     * Setup repositories recyclerView and adapter.
     */
    private fun defineReposList() {
        repos_recyclerView?.layoutManager = LinearLayoutManager(activity)
        repos_recyclerView?.addItemDecoration(MarginItemDecoration(activity!!))

        adapter = ReposAdapter(this)
        repos_recyclerView?.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initArgs()
        defineReposList()
    }

    /**
     * Gets fragment's arguments which defined in navigation graph.
     */
    private fun initArgs() {
        mViewModel.userInfo.value = args.userInfo
        mViewDataBinding.imageUrl = mViewModel.userInfo.value?.picPath
        invalidate()
    }

    override fun toggleLoading(isLoading: Boolean) {
        progress?.visibility = if (isLoading) View.VISIBLE else View.GONE

        repos_recyclerView?.animate()?.setDuration(700)?.alpha(if (isLoading) 0f else 1f)?.withEndAction {
            repos_recyclerView?.visibility = if (isLoading) View.GONE else View.VISIBLE
        }
    }

    override fun showErrorMessage(message: String?) {
        (activity as MainNavNavigator?)?.showErrorMessage(message)
    }

    override fun updateRepos(newRepos: ArrayList<GitRepo>) {
        adapter?.setRepoItems(newRepos)
    }

    override fun openWebPage(pagePath: String?) {
        val webPage = Uri.parse(pagePath)
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        if (intent.resolveActivity(activity!!.packageManager) != null) {
            activity!!.startActivity(intent)
        } else {
            showErrorMessage(getString(R.string.page_not_found))
        }
    }

}
