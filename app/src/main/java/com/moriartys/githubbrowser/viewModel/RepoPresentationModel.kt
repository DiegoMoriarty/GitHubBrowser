package com.moriartys.githubbrowser.viewModel

import com.moriartys.githubbrowser.domain.RepoData

//This could be extended to contain extra info. For example checkbox state (selected repos)
//In this simple example the data is exactly the same as in RepoData
data class RepoPresentationModel(
    val avatarUrl: String?,
    val name: String?,
    val description: String?
)

fun RepoData.toPresentationModel(): RepoPresentationModel =
    RepoPresentationModel(avatarUrl, name, description)


