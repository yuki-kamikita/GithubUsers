package com.example.githubusers.ui.page

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun UserDetailUI(user: User) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            UserProfile(user = user)
            UserRepository(user = user)
        }
    }
}

@Composable
fun UserProfile(user: User) {
    Surface(
//        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.secondary,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
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
                text = user.name,
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

                if (user.twitterUsername != null) Image(
                    painter = painterResource(id = R.drawable.twitter_blue),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(start = 8.dp)
                        .clickable(
                            enabled = true,
                            onClickLabel = "Twitter",
                            onClick = {}
                        )
                )
                if (user.email != null) Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(start = 8.dp)
                        .clickable(
                            enabled = true,
                            onClickLabel = "EMail",
                            onClick = {}
                        )
                )

            }
        }
    }
}

@Composable
fun UserRepository(user: User) {
    Text(
        text = "ここにレポジトリ情報を書く\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nスクロール確認用",
        style = typography.bodyMedium
    )
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

//private fun startActivity(url: String) {
//    val uri = Uri.parse(url)
//    val intent = Intent(ACTION_VIEW,uri)
//    startActivity(intent)
//}