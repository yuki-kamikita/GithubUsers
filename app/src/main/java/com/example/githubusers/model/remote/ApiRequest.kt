package com.example.githubusers.model.remote

import com.example.githubusers.core.Logger
import com.example.githubusers.core.data.Repositories
import com.example.githubusers.core.data.User
import com.example.githubusers.core.data.Users
import com.example.githubusers.core.dummy.userJsonStringDummy
import com.example.githubusers.core.dummy.usersJsonStringDummy
import com.google.gson.Gson
import retrofit2.Response
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
     * @return 取得失敗したらnullを返す
     */
    suspend fun getAllUsers(): Response<List<User>> {
        val response = service.getAllUsers()
        Logger.debug("$response")
        Logger.debug("${response.body()}")
        return response
    }
    fun getAllUsersDummy(): List<User> {
        return Gson().fromJson<Users>(usersJsonStringDummy, Users::class.java).userList
    }

    /**
     * @return 取得失敗したらnullを返す
     */
    suspend fun getUser(login: String): User? {
        val response = service.getUser(login)
        Logger.debug("$response")
        Logger.debug("${response.body()}")
        return if (response.isSuccessful) response.body() else null
    }
    fun getUserDummy(): User {
        return Gson().fromJson<User>(userJsonStringDummy, User::class.java)
    }

    /**
     * 指定したユーザーの全レポジトリを取得する
     *
     * @return 取得失敗したらnullを返す
     */
    suspend fun getUserRepositories(userName: String): Repositories? {
        val response = service.searchRepositories("user${':'}$userName")
        Logger.debug("$response")
        Logger.debug("${response.body()}")
        return if (response.isSuccessful) response.body() else null
    }
}