package com.moriartys.githubbrowser.framework

import com.moriartys.githubbrowser.data.ReposConsumer
import com.moriartys.githubbrowser.data.ReposErrorConsumer
import com.moriartys.githubbrowser.data.ReposSource
import com.moriartys.githubbrowser.domain.RepoData

class MockSource : ReposSource {

    private val mockData = listOf(
        RepoData("image1", "mock name xa", "description1 lore ipsum"),
        RepoData("image2", "mock name xa a1", "description2"),
        RepoData("image3", "mock name xb", "description3"),
        RepoData("image4", "mock name xb a1", "description4"),
        RepoData("image5", "mock name xc", "description5"),
        RepoData("image6", "mock name xc a2", "description6")
    )

    // This will use the query to search, but not in the same way as the GitHub APIv3 does
    override fun requestRepos(
        query: String,
        consumer: ReposConsumer,
        errorConsumer: ReposErrorConsumer
    ) {
        consumer.updateData(mockData.filter {
            if (it.name == null) false else it.name!!.contains(
                query
            )
        })

        //TODO: For testing purposes we could intercept a particular query and generate an error
    }
}