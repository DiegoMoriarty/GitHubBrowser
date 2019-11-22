package com.moriartys.githubbrowser.framework

import com.moriartys.githubbrowser.data.ReposConsumer
import com.moriartys.githubbrowser.data.ReposErrorConsumer
import com.moriartys.githubbrowser.data.ReposSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitSource : ReposSource {

    private val source: GitHubRepoService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com") //TODO: add interceptor to add headers
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        source = retrofit.create(GitHubRepoService::class.java)
    }

    override fun requestRepos(
        query: String,
        consumer: ReposConsumer,
        errorConsumer: ReposErrorConsumer
    ) {
        val call = source.getRepos(query)

        call.enqueue(object : Callback<JSonModel.RepoSearchResult> {
            override fun onResponse(
                call: Call<JSonModel.RepoSearchResult>,
                response: Response<JSonModel.RepoSearchResult>
            ) {
                if (response.code() == 200) {
                    val repoSearchResponse = response.body()!!
                    consumer.updateData(repoSearchResponse.items.map { it.toDataModel() })
                } else {
                    errorConsumer.notifyError() //TODO: Enhance with error types, maybe pass the response code also?
                }
            }

            override fun onFailure(call: Call<JSonModel.RepoSearchResult>, t: Throwable) {
                errorConsumer.notifyError() //TODO: Enhance with error types, maybe pass t.getMessage() also ?
            }
        })

    }

}