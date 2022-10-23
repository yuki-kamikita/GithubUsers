package com.example.githubusers.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubusers.R
import com.example.githubusers.core.Logger
import com.example.githubusers.core.dummy.sampleUser
import com.example.githubusers.ui.page.UserCardList
import com.example.githubusers.ui.theme.GithubUsersTheme

/**
 * 通信エラー時に表示される内容
 *
 * TODO: エラーのデフォルト値を削除
 */
@Composable
fun NetworkErrorMessage(e: String = "") {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(text = stringResource(R.string.network_error_message))
    }
    Logger.debug(e)
}

@Preview(
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun Preview() {
    GithubUsersTheme {
        NetworkErrorMessage()
    }
}