package com.moriartys.githubbrowser.data

import com.moriartys.githubbrowser.domain.RepoData

interface ReposSource {

    fun requestRepos(query: String, consumer: ReposConsumer, errorConsumer: ReposErrorConsumer)

}

interface ReposConsumer {

    fun updateData(data: List<RepoData>)

}

interface ReposErrorConsumer {

    fun notifyError()

}