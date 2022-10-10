package com.example.githubusers.ui.page

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubusers.core.data.User
import com.example.githubusers.core.dummy.sampleUser
import com.example.githubusers.ui.theme.GithubUsersTheme
import com.example.githubusers.R
import com.example.githubusers.core.data.Licence
import com.example.githubusers.core.data.Repository
import com.example.githubusers.ui.component.CenterProgressIndicator
import com.example.githubusers.viewModel.UserDetailViewModel
import com.google.gson.annotations.SerializedName

/**
 * ユーザー詳細画面
 *
 * レポジトリをクリックしたらブラウザで該当レポジトリを表示
 */
@Composable
fun UserDetailUI(user: User, viewModel: UserDetailViewModel = UserDetailViewModel(user)) {
    val repositories by viewModel.repositories
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface,
    ) {
        LazyColumn{
            item { UserProfile(user = user) }
            if (repositories != null) items(repositories!!.items!!) { repository ->
                RepositoryCard(repository = repository)
            }
            else item { CenterProgressIndicator() }
        }
    }
}

@Composable
fun UserProfile(user: User) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.secondary,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                text = user.name ?: user.login,
                style = typography.displayMedium
            )
            if (user.bio != null) Text(
                text = user.bio,
                style = typography.bodyMedium
            )
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End
            ) {
                if (user.twitterUsername != null) TwitterLinkIcon(user.twitterUsername)
                if (user.email != null) EmailLinkIcon(user.email)
                if (user.blog != null) BlogLinkIcon(user.blog)
            }
        }
    }
}

@Composable
fun TwitterLinkIcon(twitterUsername: String) {
    val context = LocalContext.current
    Image(
        painter = painterResource(id = R.drawable.twitter_blue),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .padding(all = 8.dp)
            .clickable(
                enabled = true,
                onClickLabel = "Twitter",
                onClick = { toBrowser(context, "https://twitter.com/${twitterUsername}") }
            )
    )
}

@Composable
fun EmailLinkIcon(email: String) {
    val context = LocalContext.current
    Icon(
        imageVector = Icons.Default.Email,
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .padding(all = 8.dp)
            .clickable(
                enabled = true,
                onClickLabel = "EMail",
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_SENDTO,
                        Uri.parse("mailto:${email}")
                    )
                    context.startActivity(intent)
                }
            )
    )
}

@Composable
fun BlogLinkIcon(blog: String) {
    val context = LocalContext.current
    Icon(
        imageVector = Icons.Default.Language,
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .padding(all = 8.dp)
            .clickable(
                enabled = true,
                onClickLabel = "Url",
                onClick = { toBrowser(context, blog) }
            )
    )
}

@Composable
fun RepositoryCard(repository: Repository) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .clickable { toBrowser(context, repository.htmlUrl) },
        ) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(repository.owner.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Column (
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
            ){
                Row{
                    Text(
                        text = repository.name,
                        style = typography.titleMedium
                    )
                    if (repository.language != null) Text(text = " / ${repository.language}")
                }
                if (repository.description != null) Text(
                    text = repository.description,
                    style = typography.bodyMedium
                )
                Text(
                    text = "update at: ${repository.updatedAt}",
                    style = typography.bodySmall,
                )
        }

        }
    }
}

/**
 * URLを指定してブラウザ遷移する
 */
fun toBrowser(context: Context, url: String) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    )
    context.startActivity(intent)
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
fun Preview() {
    GithubUsersTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            UserDetailUI(sampleUser)
        }
    }
}

@Preview(
    showBackground = true,
    name = "Repository Card Light"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Repository Card Dark"
)
@Composable
fun RepositoryCardPreview() {
    GithubUsersTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            RepositoryCard(Repository(
                id = 1,
                name = "SampleRepository",
                owner = sampleUser,
                htmlUrl = "https://github.com/",
                description = "レポジトリの説明",
                createdAt = "",
                updatedAt = "2013-01-05T17:58:47Z",
                pushedAt = "",
                stargazersCount = 1,
                watchersCount = 1,
                language = "Kotlin",
                forksCount = 1,
                visibility = "public",
                licence = Licence(
                    key = "mit",
                    name = "MIT License",
                    url = "https://api.github.com/licenses/mit",
                    spdxId = "MIT",
                    nodeId = "MDc6TGljZW5zZW1pdA==",
                    htmlUrl = "https://api.github.com/licenses/mit"
                )
            ))
        }
    }
}