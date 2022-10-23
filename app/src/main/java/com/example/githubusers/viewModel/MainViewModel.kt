package com.example.githubusers.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.githubusers.core.data.User
import com.example.githubusers.model.remote.ApiRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class MainViewModel: ViewModel() {
    val userListState: MutableState<UserListState> = mutableStateOf(UserListState.Loading)

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiRequest().getAllUsers()

            // TODO: sealed class の中に入れる
            userListState.value = if (response.isSuccessful) {
                response.body()?.let {
                    UserListState.Success(it)
                } ?:run {
                    UserListState.Failure(response.errorBody())
                }
            } else {
                UserListState.Failure(response.errorBody())
            }

//            users.value = ApiRequest().getAllUsersDummy() // 開発用のダミーデータ
        }
    }

}

sealed class UserListState {
    object Loading: UserListState()
    data class Success(val body: List<User>): UserListState()
    data class Failure(val reason: ResponseBody?): UserListState() // TODO: エラー時にログ出力する

    fun body(): List<User> {
        return when (this) {
            is Success -> this.body
            else -> listOf(User())
        }
    }
}

