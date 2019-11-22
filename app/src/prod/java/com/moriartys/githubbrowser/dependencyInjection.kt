package com.moriartys.githubbrowser

import com.moriartys.githubbrowser.data.ReposSource
import com.moriartys.githubbrowser.framework.RetrofitSource
import com.moriartys.githubbrowser.viewModel.RepoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoListViewModelModule = module {
    viewModel {
        RepoListViewModel(get())
    }
}

val sourcesModule = module {
    fun provideSource(): ReposSource {
        return RetrofitSource()
    }

    factory { provideSource() }
}