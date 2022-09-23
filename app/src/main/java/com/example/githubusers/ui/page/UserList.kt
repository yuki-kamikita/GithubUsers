package com.example.githubusers.ui.page

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubusers.core.data.User
import com.example.githubusers.ui.theme.GithubUsersTheme

@Composable
fun UserListUI(
    userList: List<User>,
    onCardClick: (String) -> Unit = {},
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        UserCardList(userList, onCardClick)
    }

}

@Composable
fun UserCardList(users: List<User>, onCardClick: (String) -> Unit = {}) {
    LazyColumn {
        items(users) { user ->
            UserCard(user, onCardClick)
        }
    }
}

@Composable
fun UserCard(user: User, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .clickable { onClick(user.login) },
//        elevation = CardDefaults.cardElevation()
    ) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {

                Text(text = user.login)

            }
        }

    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun ListPreview() {
    val sampleUser = User(
        login = "octocat",
        id = 1,
        nodeId = "MDQ6VXNlcjE=",
        avatarUrl = "https://github.com/images/error/octocat_happy.gif",
        gravatarId = "",
        url = "https://api.github.com/users/octocat",
        htmlUrl = "https://github.com/octocat",
        followersUrl = "https://api.github.com/users/octocat/followers",
        followingUrl = "https://api.github.com/users/octocat/following{/otherUser}",
        gistsUrl = "https://api.github.com/users/octocat/gists{/gist_id}",
        starredUrl = "https://api.github.com/users/octocat/starred{/owner}{/repo}",
        subscriptionsUrl = "https://api.github.com/users/octocat/subscriptions",
        organizationsUrl = "https://api.github.com/users/octocat/orgs",
        reposUrl = "https://api.github.com/users/octocat/repos",
        eventsUrl = "https://api.github.com/users/octocat/events{/privacy}",
        receivedEventsUrl = "https://api.github.com/users/octocat/received_events",
        type = "User",
        siteAdmin = false,
        bio = "ここに自己紹介がつらつらと書かれてそれを参考にユーザーの詳細を見に行くかどうか判断する想定"
    )
    GithubUsersTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            UserCardList(
                listOf(sampleUser, sampleUser, sampleUser)
            )
        }
    }
}