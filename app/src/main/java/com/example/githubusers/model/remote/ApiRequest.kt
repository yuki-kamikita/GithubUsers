package com.example.githubusers.model.remote

import com.example.githubusers.core.Logger
import com.example.githubusers.core.data.User
import com.example.githubusers.core.data.Users
import com.example.githubusers.core.dummy.userJsonStringDummy
import com.example.githubusers.core.dummy.usersJsonStringDummy
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRequest {
    private val domain = "https://api.github.com/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(domain)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(Endpoint::class.java)

    /**
     * @return 取得失敗したらnullを返す TODO: ここのエラーの処理ちゃんとする
     */
    suspend fun getAllUsers(): List<User>? {
        val response = service.allUsers()
        Logger.debug("$response")
        return if (response.isSuccessful) response.body() else null
    }
    fun getAllUsersDummy(): List<User> {
        return Gson().fromJson<Users>(usersJsonStringDummy, Users::class.java).userList
    }

    /**
     * @return 取得失敗したらnullを返す TODO: ここのエラーの処理ちゃんとする
     */
    suspend fun getUser(login: String): User? {
        val response = service.user(login)
        Logger.debug("$response")
        return if (response.isSuccessful) response.body() else null
    }
    fun getUserDummy(): User {
        return Gson().fromJson<User>(userJsonStringDummy, User::class.java)
    }
}