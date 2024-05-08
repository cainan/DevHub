package com.cso.devhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cso.devhub.ui.theme.DevHubTheme
import com.cso.devhub.webclient.GitHubWebClient

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DevHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomePage(user = "cainan")
                }
            }
        }
    }


}

@Composable
fun HomePage(user: String, webClient: GitHubWebClient = GitHubWebClient()) {

    val foundUser by webClient.profileInfo(user)
        .collectAsState(initial = null)
    foundUser?.let {

        Column {
            val boxHeight = remember {
                150.dp
            }
            val imageHeight = remember {
                boxHeight
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.Gray,
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    )
                    .height(boxHeight)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(
                        LocalContext.current
                    ).data(it.avatar).crossfade(true)
                        .build(),
                    contentDescription = "",
                    placeholder = painterResource(R.drawable.user_placeholder),
                    modifier = Modifier
                        .offset(y = imageHeight / 2)
                        .size(imageHeight)
                        .align(Alignment.BottomCenter)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(imageHeight / 2))

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = it.name,
                    fontSize = 32.sp,
                )
                Text(
                    text = it.login,
                    fontSize = 18.sp,
                )
                Text(
                    text = it.bio ?: "",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DevHubTheme {
        HomePage(user = "cainan")
    }
}