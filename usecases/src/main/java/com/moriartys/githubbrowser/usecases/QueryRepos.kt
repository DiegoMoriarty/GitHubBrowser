package com.moriartys.githubbrowser.usecases

import com.moriartys.githubbrowser.data.ReposConsumer
import com.moriartys.githubbrowser.data.ReposErrorConsumer
import com.moriartys.githubbrowser.data.ReposSource

class QueryRepos(private val source: ReposSource) {

    operator fun invoke(query: String, consumer: ReposConsumer, errorConsumer: ReposErrorConsumer) =
        source.requestRepos(query, consumer, errorConsumer)

}