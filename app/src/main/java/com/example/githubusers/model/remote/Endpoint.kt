package com.example.githubusers.model.remote

import com.example.githubusers.core.data.Repositories
import com.example.githubusers.core.data.User
import retrofit2.Response
import retrofit2.http.*

interface Endpoint{
    /**
     * ユーザー一覧
     *
     * https://docs.github.com/en/rest/users/users#list-users
     */
    @GET("users")
    suspend fun getAllUsers(): Response<List<User>>

    /**
     * ユーザー詳細
     *
     * https://docs.github.com/en/rest/users/users#get-a-user
     */
    @GET("users/{login}")
    suspend fun getUser(
        @Path("login") login: String
    ): Response<User>

    /**
     * レポジトリ検索
     *
     * https://docs.github.com/ja/search-github/searching-on-github/searching-for-repositories
     * https://docs.github.com/ja/rest/search#constructing-a-search-query
     */
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") q: String
    ): Response<Repositories>

}