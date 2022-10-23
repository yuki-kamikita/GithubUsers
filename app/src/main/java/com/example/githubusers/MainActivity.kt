package com.example.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githubusers.ui.destination.AllUser
import com.example.githubusers.ui.destination.FavoriteUser
import com.example.githubusers.ui.destination.UserDetail
import com.example.githubusers.ui.page.UserDetailUI
import com.example.githubusers.ui.page.UserListUI
import com.example.githubusers.ui.theme.GithubUsersTheme
import com.example.githubusers.viewModel.MainViewModel

class MainActivity(viewModel: MainViewModel = MainViewModel()) : ComponentActivity() {
    private val userList by viewModel.userList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }
