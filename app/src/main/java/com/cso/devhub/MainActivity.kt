package com.cso.devhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.cso.devhub.ui.screen.AuthenticationScreen
import com.cso.devhub.ui.screen.ProfileScreen
import com.cso.devhub.ui.theme.DevHubTheme

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DevHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {

                        var screenState: AppScreens by remember {
                            mutableStateOf(AppScreens.Authentication)
                        }
                        var user by remember {
                            mutableStateOf("")
                        }

                        when (screenState) {
                            AppScreens.Authentication -> {
                                AuthenticationScreen(onEnterClick = {
                                    user = it
                                    screenState = AppScreens.Profile
                                })
                            }

                            AppScreens.Profile -> {
                                ProfileScreen(user = user)
                            }

                        }

                    }
                }
            }
        }

    }
}

sealed class AppScreens {
    data object Authentication : AppScreens()
    data object Profile : AppScreens()

}
