package com.sverbusoft.genesis_test.ui.fragments.favorite

import androidx.lifecycle.ViewModel
import com.sverbusoft.genesis_test.data.features.favorite.repository.FavoriteRepositoryImpl
import com.sverbusoft.genesis_test.domain.favorite.repository.FavoriteRepository
import com.sverbusoft.genesis_test.domain.repos.model.ReposModel

class FavoriteViewModel : ViewModel() {
    private val repository: FavoriteRepository = FavoriteRepositoryImpl();

    val reposPages by lazy {
        repository.getPagedList()
    }

    fun searchRepos(name: String) {
        repository.searchRepos(name);
    }

    fun deleteFromFavorite(item: ReposModel) {
        repository.deleteRepos(item)
    }

}