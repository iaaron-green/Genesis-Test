package com.sverbusoft.genesis_test.ui.fragments.repos

import androidx.lifecycle.ViewModel
import com.sverbusoft.genesis_test.data.features.repos.model.ReposModel
import com.sverbusoft.genesis_test.data.features.repos.repository.ReposRepository

class ReposViewModel : ViewModel() {
    private val reposRepository: ReposRepository = ReposRepository();

    val reposPages by lazy {
        reposRepository.getPagedList()
    }

    fun searchRepos(name: String) {
        reposRepository.searchRepos(name);
    }

    fun addToFavorite(item: ReposModel) {
        reposRepository.addToFavorite(item)
    }
}