package com.example.githubusers.model.remote

import com.example.githubusers.core.data.User
import retrofit2.Response
import retrofit2.http.*

interface Endpoint{
    /**
     * ユーザー一覧
     * https://docs.github.com/en/rest/users/users#list-users
     */
    @GET("users")
    suspend fun allUsers(): Response<List<User>>

    /**
     * ユーザー詳細
     * https://docs.github.com/en/rest/users/users#get-a-user
     */
    @GET("users/{login}")
    suspend fun user(
        @Path("login") login: String
    ): Response<User>

}