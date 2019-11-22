package com.moriartys.githubbrowser.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moriartys.githubbrowser.data.ReposConsumer
import com.moriartys.githubbrowser.data.ReposErrorConsumer
import com.moriartys.githubbrowser.data.ReposSource
import com.moriartys.githubbrowser.domain.RepoData
import com.moriartys.githubbrowser.usecases.QueryRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


data class RepoListViewModel(val source: ReposSource) : ViewModel() {

    private val queryRepos = QueryRepos(source)

    val list = MutableLiveData<List<RepoPresentationModel>>(emptyList())

    val error: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>(null)
    }

    fun onSearchRequested(query: String) = GlobalScope.launch(Dispatchers.Main) {
        withContext(Dispatchers.IO) {
            queryRepos(query, object : ReposConsumer {
                override fun updateData(data: List<RepoData>) {
                    list.postValue(data.map(RepoData::toPresentationModel))
                    error.postValue(null)
                }
            }, object : ReposErrorConsumer {
                override fun notifyError() {
                    list.postValue(emptyList())
                    error.postValue("error")
                }
            })
        }
    }

}
