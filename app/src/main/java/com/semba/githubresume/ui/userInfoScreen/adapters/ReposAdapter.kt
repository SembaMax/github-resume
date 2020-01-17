package com.semba.githubresume.ui.userInfoScreen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.semba.githubresume.R
import com.semba.githubresume.base.BaseViewHolder
import com.semba.githubresume.data.GitRepo
import com.semba.githubresume.ui.userInfoScreen.UserInfoNavigator
import com.semba.githubresume.utils.extensions.toFormattedString
import kotlinx.android.synthetic.main.repo_item.view.*

/**
 * Created by SeMbA on 2019-12-07.
 */
class ReposAdapter(private val delegate: UserInfoNavigator) : RecyclerView.Adapter<BaseViewHolder>() {

    private val repos: ArrayList<GitRepo> = ArrayList()

    fun setRepoItems(newItems: ArrayList<GitRepo>)
    {
        val diffCallback = RepoDiffCallback(repos, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        repos.clear()
        repos.addAll(newItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class ViewHolder(view: View) : BaseViewHolder(view)
    {
        private val name = view.repo_name
        private val language = view.repo_language
        private val createdOn = view.created_on
        private val updatedOn = view.updated_on
        private val stars = view.stars_count
        private val forks = view.forks_count
        private val watches = view.watch_count
        private val openBtn = view.open_btn

        override fun onBind(position: Int) {
            name?.text = repos[position].name
            language?.text = repos[position].language
            stars?.text = repos[position].stars?.toString()
            forks?.text = repos[position].forks?.toString()
            watches?.text = repos[position].watchers?.toString()
            createdOn?.text = repos[position].createdAt?.toFormattedString()
            updatedOn?.text = repos[position].updatedAt?.toFormattedString()

            openBtn?.setOnClickListener {
                delegate.openWebPage(repos[position].pagePath)
            }
        }
    }
}