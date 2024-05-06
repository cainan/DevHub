package com.cso.devhub.webclient

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/cainan")
    suspend fun getProfileStatic(): Response<GitHubProfileWeb>

    @GET("users/{user}")
    suspend fun findProfileBy(@Path("user") username: String): GitHubProfileWeb
}