package com.semba.githubresume.ui.mainNavScreen

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.semba.githubresume.BR
import com.semba.githubresume.R
import com.semba.githubresume.base.BaseActivity
import com.semba.githubresume.databinding.ActivityMainNavBinding
import com.semba.githubresume.utils.ViewModelProviderFactory
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_main_nav.*
import javax.inject.Inject

class MainNavActivity : BaseActivity<ActivityMainNavBinding, MainNavViewModel>(), MainNavNavigator {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    override fun getLayoutId(): Int {
        return R.layout.activity_main_nav
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun afterInjection() {
        this.assignViewModel(viewModelFactory, MainNavViewModel::class.java)
        this.mViewModel.setNavigator(this)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.main_navigation_graph).navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setupCustomActionBar()
    }

    private fun setupCustomActionBar() {
        setSupportActionBar(toolbar_actionbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.elevation = 3f
        val backBtn = toolbar_actionbar?.findViewById<ImageView>(R.id.action_bar_back_btn)
        backBtn?.setOnClickListener {
            onBackPressed()
        }
    }

    override fun toggleLoading(isLoading: Boolean) {

    }

    override fun showErrorMessage(message: String?) {
        Snackbar.make(main_layout,message?: getString(R.string.error), Snackbar.LENGTH_SHORT).show()
    }

    override fun switchFragmentOptions(which: MainFragments) {

        val title = toolbar_actionbar?.findViewById<TextView>(R.id.action_bar_title)
        val backBtn = toolbar_actionbar?.findViewById<ImageView>(R.id.action_bar_back_btn)

        when (which)
        {
            MainFragments.Start ->
            {
                backBtn?.visibility = View.INVISIBLE
                title?.text = getString(R.string.main)
            }

            MainFragments.UserInfo ->
            {
                backBtn?.visibility = View.VISIBLE
                title?.text = getString(R.string.user)
            }
        }
    }

}
