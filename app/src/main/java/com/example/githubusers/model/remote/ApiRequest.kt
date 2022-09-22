package com.example.githubusers.model.remote

import com.example.githubusers.core.Logger
import com.example.githubusers.core.data.User
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
}