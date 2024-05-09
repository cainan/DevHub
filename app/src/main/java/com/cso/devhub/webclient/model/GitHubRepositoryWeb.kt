package com.cso.devhub.webclient.model

import com.cso.devhub.model.GitHubModel

data class GitHubRepositoryWeb(
    val name: String,
    val description: String?,
)

fun GitHubRepositoryWeb.toGitHubRepositoryUiModel(): GitHubModel {
    return GitHubModel(name = name, description = description ?: "")
}