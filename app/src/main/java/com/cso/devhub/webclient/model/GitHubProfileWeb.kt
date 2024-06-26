package com.cso.devhub.webclient.model

import com.cso.devhub.ui.screen.ProfileUiState
import com.squareup.moshi.Json

data class GitHubProfileWeb(
    val login: String,
    @field:Json(name = "avatar_url")
    val avatar: String,
    val name: String,
    val bio: String?,
)

fun GitHubProfileWeb.toProfileUiState(): ProfileUiState {
    return ProfileUiState(
        user = login,
        image = avatar,
        name = name,
        bio = bio ?: ""
    )
}

/*

{
  "login": "cainan",
  "id": 977227,
  "node_id": "MDQ6VXNlcjk3NzIyNw==",
  "avatar_url": "https://avatars.githubusercontent.com/u/977227?v=4",
  "gravatar_id": "",
  "url": "https://api.github.com/users/cainan",
  "html_url": "https://github.com/cainan",
  "followers_url": "https://api.github.com/users/cainan/followers",
  "following_url": "https://api.github.com/users/cainan/following{/other_user}",
  "gists_url": "https://api.github.com/users/cainan/gists{/gist_id}",
  "starred_url": "https://api.github.com/users/cainan/starred{/owner}{/repo}",
  "subscriptions_url": "https://api.github.com/users/cainan/subscriptions",
  "organizations_url": "https://api.github.com/users/cainan/orgs",
  "repos_url": "https://api.github.com/users/cainan/repos",
  "events_url": "https://api.github.com/users/cainan/events{/privacy}",
  "received_events_url": "https://api.github.com/users/cainan/received_events",
  "type": "User",
  "site_admin": false,
  "name": "Cainã Oliveira",
  "company": null,
  "blog": "",
  "location": null,
  "email": null,
  "hireable": null,
  "bio": null,
  "twitter_username": null,
  "public_repos": 12,
  "public_gists": 0,
  "followers": 2,
  "following": 2,
  "created_at": "2011-08-13T02:42:51Z",
  "updated_at": "2024-04-30T23:35:09Z"
 */