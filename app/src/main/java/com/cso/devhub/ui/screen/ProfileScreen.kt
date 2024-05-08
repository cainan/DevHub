package com.cso.devhub.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.cso.devhub.R
import com.cso.devhub.ui.theme.DevHubTheme
import com.cso.devhub.webclient.GitHubWebClient
import com.cso.devhub.webclient.toProfileUiState

@Composable
fun ProfileScreen(user: String, webClient: GitHubWebClient = GitHubWebClient()) {

    val foundUser by webClient.profileInfo(user)
        .collectAsState(initial = null)
    foundUser?.let { userProfile ->
        Profile(userProfile.toProfileUiState())
    }
}

@Composable
fun Profile(state: ProfileUiState) {
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
                ).data(state.image).crossfade(true)
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
                text = state.name,
                fontSize = 32.sp,
            )
            Text(
                text = state.user,
                fontSize = 18.sp,
            )
            Text(
                text = state.bio,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    DevHubTheme {
        Profile(
            state = ProfileUiState(
                user = "teste userId",
                image = "https://avatars.githubusercontent.com/u/977227?v=4",
                name = "Teste name",
                bio = "Teste bio"
            )
        )
    }
}