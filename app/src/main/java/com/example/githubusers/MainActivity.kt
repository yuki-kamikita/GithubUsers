package com.example.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.githubusers.core.Logger
import com.example.githubusers.core.data.User
import com.example.githubusers.model.remote.ApiRequest
import com.example.githubusers.ui.destination.AllUser
import com.example.githubusers.ui.destination.FavoriteUser
import com.example.githubusers.ui.destination.UserDetail
import com.example.githubusers.ui.page.UserDetailUI
import com.example.githubusers.ui.page.UserListUI
import com.example.githubusers.ui.theme.GithubUsersTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            val userList = ApiRequest().getAllUsers()!! // FIXME: アクセス制限になるとnullが入りクラッシュする
//            val userList = ApiRequest().getAllUsersDummy() // 開発用のダミーデータ

            CoroutineScope(Dispatchers.Main).launch {
                setContent {
                    GithubUsersTheme {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = AllUser.route,
                        ) {
                            composable(route = AllUser.route) {
                                UserListUI(
                                    userList,
                                    onCardClick = { login ->
                                        navController.navigateSingleTopTo("${UserDetail.route}/$login")
                                    },
                                )
                            }
                            composable(
                                route = UserDetail.routeWithArgs,
                                arguments = UserDetail.arguments
                            ) { user ->
                                val login = user.arguments?.getString(UserDetail.loginArg)!!
                                UserDetailUI(login)
                            }
                            composable(route = FavoriteUser.route) {}
                        }
                    }
                }
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }
