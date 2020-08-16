package com.sverbusoft.genesis_test.ui.fragments.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sverbusoft.genesis_test.data.features.favorite.repository.FavoriteRepository
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem
import com.sverbusoft.genesis_test.data.features.repos.repository.ReposRepository

class FavoriteViewModel : ViewModel() {
    private val repository: FavoriteRepository = FavoriteRepository();

    val reposPages by lazy {
        repository.getPagedList()
    }

    fun searchRepos(name: String){
        repository.searchRepos(name);
    }

}