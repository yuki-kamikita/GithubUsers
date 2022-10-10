package com.example.githubusers.core.data

import com.google.gson.annotations.SerializedName

/**
 * Githubのレポジトリ一覧APIから取得した内容
 *
 * https://docs.github.com/ja/search-github/searching-on-github/searching-for-repositories
 */
data class Repositories(
    @SerializedName("total_count")
    val totalCount: Int? = null,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,
    @SerializedName("items")
    val items: List<Repository>? = null
)

data class Repository(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: User,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("pushed_at")
    val pushedAt: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    @SerializedName("language")
    val language: String?,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("visibility")
    val visibility: String,
    @SerializedName("licence")
    val licence: Licence?,
)

data class Licence(
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("spdx_id")
    val spdxId: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("html_url")
    val htmlUrl: String,
)