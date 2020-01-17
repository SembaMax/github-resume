package com.semba.githubresume.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.semba.githubresume.R

/**
 * Created by SeMbA on 2019-12-08.
 */

object DataBindingAdapter
{
    @JvmStatic
    @BindingAdapter("profileImage")
    fun loadImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .circleCrop()
            .placeholder(R.drawable.user_prof)
            .into(view)
    }

}