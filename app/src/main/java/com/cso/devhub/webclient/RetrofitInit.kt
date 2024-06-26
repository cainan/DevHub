package com.cso.devhub.webclient

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInit {
    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val gitHubService: GitHubService get() = retrofit.create(GitHubService::class.java)

}

