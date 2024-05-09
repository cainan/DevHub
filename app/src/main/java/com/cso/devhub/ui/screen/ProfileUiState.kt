package com.cso.devhub.ui.screen

import com.cso.devhub.model.GitHubModel

data class ProfileUiState(
    val user: String = "",
    val image: String = "",
    val name: String = "",
    val bio: String = "",
    val repositories: List<GitHubModel> = emptyList(),
)