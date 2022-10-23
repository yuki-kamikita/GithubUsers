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
import okhttp3.ResponseBody

class UserDetailViewModel(userName: String): ViewModel() {
    val user: MutableState<UserDetailState> = mutableStateOf(UserDetailState.Loading)
    val repositories: MutableState<Repositories?> = mutableStateOf(null)

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiRequest().getUser(userName)
            user.value = if (response.isSuccessful) {
                response.body()?.let {
                    repositories.value = ApiRequest().getUserRepositories(it.login)
                    UserDetailState.Success(it)
                } ?:run {
                    UserDetailState.Failure(response.errorBody())
                }
            } else {
                UserDetailState.Failure(response.errorBody())
            }
            Logger.debug("$repositories")
        }
    }

}

sealed class UserDetailState {
    object Loading: UserDetailState()
    data class Success(val body: User): UserDetailState()
    data class Failure(val reason: ResponseBody?): UserDetailState() // TODO: エラー時にログ出力する

    fun body(): User {
        return when (this) {
            is Success -> this.body
            else -> User()
        }
    }
}

