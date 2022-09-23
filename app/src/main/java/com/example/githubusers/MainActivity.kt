package com.example.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.githubusers.model.remote.ApiRequest
import com.example.githubusers.ui.page.UserList
import com.example.githubusers.ui.theme.GithubUsersTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            val userList = ApiRequest().getAllUsers()!!

            CoroutineScope(Dispatchers.Main).launch {
                setContent {
                    GithubUsersTheme {
                        UserList(userList)
                    }
                }
            }
        }
    }
}
