package com.semba.githubresume.data

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import com.semba.githubresume.R
import kotlinx.android.parcel.Parcelize


/**
 * Created by SeMbA on 2019-12-07.
 */
@Parcelize
data class UserInfo (
    @SerializedName("id") var id: Long?,
    @SerializedName("login") var username: String?,
    @SerializedName("avatar_url") var picPath: String?,
    @SerializedName("html_url") var pagePath: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("public_repos") var reposCount: Int?,
    @SerializedName("followers") var followersCount: Int?,
    @SerializedName("following") var followingCount: Int?
) : Parcelable