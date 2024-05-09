package com.cso.devhub.webclient

import com.cso.devhub.webclient.model.GitHubProfileWeb
import com.cso.devhub.webclient.model.GitHubRepositoryWeb
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}")
    suspend fun findProfileBy(@Path("user") username: String): GitHubProfileWeb

    @GET("users/{user}/repos")
    suspend fun findUserRepositories(@Path("user") username: String): List<GitHubRepositoryWeb>
}