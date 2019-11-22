package com.moriartys.githubbrowser.framework

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GitHubRepoService {
    @Headers("Accept: application/vnd.github.v3+json") //TODO: add interceptor for all
    @GET("search/repositories?")
    fun getRepos(@Query("q") query: String): Call<JSonModel.RepoSearchResult>
}


