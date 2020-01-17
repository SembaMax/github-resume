package com.semba.githubresume.ui.userInfoScreen.adapters

import androidx.recyclerview.widget.DiffUtil
import com.semba.githubresume.data.GitRepo

/**
 * Created by SeMbA on 2020-01-17.
 */
class RepoDiffCallback(private val oldList: List<GitRepo>, private val newList: List<GitRepo>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id === newList.get(newItemPosition).id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition].id == newList.get(newPosition).id
    }


    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}