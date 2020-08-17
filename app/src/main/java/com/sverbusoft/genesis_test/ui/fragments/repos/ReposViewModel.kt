package com.sverbusoft.genesis_test.ui.fragments.repos

import androidx.lifecycle.ViewModel
import com.sverbusoft.genesis_test.data.features.repos.repository.ReposRepositoryImpl
import com.sverbusoft.genesis_test.domain.repos.model.ReposModel
import com.sverbusoft.genesis_test.domain.repos.repository.ReposRepository
import io.reactivex.disposables.CompositeDisposable

class ReposViewModel : ViewModel() {
    private var reposRepository: ReposRepository
    private val compositeDisposable = CompositeDisposable()

    init {
        reposRepository = ReposRepositoryImpl(compositeDisposable);
    }

    val reposPages by lazy {
        reposRepository.getPagedList()
    }

    fun searchRepos(name: String) {
        reposRepository.searchRepos(name);
    }

    fun addToFavorite(item: ReposModel) {
        reposRepository.addToFavorite(item)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}