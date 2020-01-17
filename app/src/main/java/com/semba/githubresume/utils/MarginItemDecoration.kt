package com.semba.githubresume.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.semba.githubresume.R

/**
 * Created by SeMbA on 2019-12-07.
 */
class MarginItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private var margin = 0

    init {
        margin = context.resources.getDimensionPixelSize(R.dimen.repo_item_margin)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(margin, margin, margin, margin)
    }
}