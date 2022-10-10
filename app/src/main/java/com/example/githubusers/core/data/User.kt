package com.example.githubusers.core.data

import com.google.gson.annotations.SerializedName

/**
 * Githubのユーザー一覧APIから取得した内容
 *
 * https://docs.github.com/en/rest/users/users#list-users
 */
data class User(
    @SerializedName("login")
    val login: String = "",
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("company")
    val company: String? = null,
    @SerializedName("blog")
    val blog: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
    @SerializedName("twitter_username")
    val twitterUsername: String? = null,
)

data class Users(
    @SerializedName("users")
    val userList: List<User>
)