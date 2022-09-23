package com.example.githubusers.ui.destination

import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * ユーザー周りの画面遷移
 *
 * 今回のアプリでここが全て
 */
interface UserRallyDestination {
    val route: String
}

/**
 * 以下、画面リストアップ
 */
object AllUser : UserRallyDestination {
    override val route = "all"
}

object UserDetail : UserRallyDestination {
    override val route = "detail"
    const val loginArg = "login"
    val routeWithArgs = "${route}/{${loginArg}}"
    val arguments = listOf(
        navArgument(loginArg) { type = NavType.StringType }
    )
}

object FavoriteUser : UserRallyDestination {
    override val route = "favorite"
}

