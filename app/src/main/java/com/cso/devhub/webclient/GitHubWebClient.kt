package com.cso.devhub.webclient

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.cso.devhub.ui.screen.ProfileUiState
import com.cso.devhub.webclient.model.toGitHubRepositoryUiModel
import com.cso.devhub.webclient.model.toProfileUiState

class GitHubWebClient {

    companion object {
        private const val TAG = "GitHubWebClient"
    }

    private val service: GitHubService = RetrofitInit().gitHubService

    var uiState by mutableStateOf(ProfileUiState())
        private set

    suspend fun findProfileBy(user: String) {
        try {
            val toProfileUiState = service.findProfileBy(user).toProfileUiState()
            val toGitHubRepositoryUiModel =
                service.findUserRepositories(user).map { it.toGitHubRepositoryUiModel() }
            uiState = toProfileUiState.copy(repositories = toGitHubRepositoryUiModel)
        } catch (e: Exception) {
            Log.e(TAG, "findProfileBy: fail to retrieve user", e)
        }
    }

}