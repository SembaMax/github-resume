package com.semba.githubresume.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment

/**
 * Created by SeMbA on 2019-12-07.
 */
abstract class BaseFragment <T: ViewDataBinding, V: ViewModel> : DaggerFragment() {

    var mActivity: BaseActivity<*, *>? = null
    lateinit var mRootView: View
    lateinit var mViewDataBinding: T
    lateinit var mViewModel: V

    abstract fun getLayoutId(): Int
    abstract fun getBindingVariable(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            this.mActivity = context
        }
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    fun assignViewModel(viewModelFactory: ViewModelProvider.Factory, vmClass: Class<V>): V {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(vmClass)
        return mViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initBinding() {
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mRootView = mViewDataBinding.root
        initBinding()
        return mRootView
    }

    fun isNetworkAvailable() = mActivity?.isNetworkAvailable()

    fun invalidate() {
        mViewDataBinding.invalidateAll()
    }
}
