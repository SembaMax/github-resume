package com.semba.githubresume.data

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by SeMbA on 2019-12-07.
 */
data class GitRepo (
    @SerializedName("id") var id: Long?,
    @SerializedName("name") var name: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("html_url") var pagePath: String?,
    @SerializedName("created_at") var createdAt: Date?,
    @SerializedName("updated_at") var updatedAt: Date?,
    @SerializedName("size") var size: Int?,
    @SerializedName("watchers") var watchers: Int?,
    @SerializedName("forks") var forks: Int?,
    @SerializedName("stargazers_count") var stars: Int?,
    @SerializedName("language") var language: String?
)