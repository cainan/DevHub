package com.cso.devhub.webclient

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GitHubWebClient {

    private val service: GitHubService = RetrofitInit().gitHubService

    fun profileInfo(user: String): Flow<GitHubProfileWeb> = flow {
        val latestNews = service.findProfileBy(user)
        emit(latestNews) // Emits the result of the request to the flow
    }

}