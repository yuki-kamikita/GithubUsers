package com.example.githubusers.ui.page

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubusers.core.data.User
import com.example.githubusers.core.dummy.sampleUser
import com.example.githubusers.ui.theme.GithubUsersTheme

@Composable
fun UserListUI(
    userList: List<User>,
    onCardClick: (String) -> Unit = {},
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
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
//                if (user.bio != null) Text(text = user.bio)
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