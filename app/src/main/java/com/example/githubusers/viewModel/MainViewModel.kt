package com.example.githubusers.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.githubusers.core.data.User
import com.example.githubusers.model.remote.ApiRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val userList: MutableState<List<User>> = mutableStateOf(listOf())

    init {
        CoroutineScope(Dispatchers.IO).launch {
            userList.value = ApiRequest().getAllUsers()!! // FIXME: アクセス制限になるとnullが入りクラッシュする
//            users.value = ApiRequest().getAllUsersDummy() // 開発用のダミーデータ
        }
    }

}