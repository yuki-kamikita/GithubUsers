package com.example.githubusers.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.githubusers.core.Logger
import com.example.githubusers.core.data.Repositories
import com.example.githubusers.core.data.Repository
import com.example.githubusers.core.data.User
import com.example.githubusers.model.remote.ApiRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailViewModel(userName: String): ViewModel() {
    val user: MutableState<User> = mutableStateOf(User())
    val repositories: MutableState<Repositories?> = mutableStateOf(null)

    init {
        CoroutineScope(Dispatchers.IO).launch {
            user.value = ApiRequest().getUser(userName)!! // FIXME: アクセス制限になるとnullが入りクラッシュする
            repositories.value = ApiRequest().getUserRepositories(user.value.login)
            Logger.debug("$repositories")
        }
    }

}