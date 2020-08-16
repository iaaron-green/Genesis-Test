package com.sverbusoft.genesis_test.ui.fragments.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem
import com.sverbusoft.genesis_test.data.features.repos.repository.ReposRepository

class ReposViewModel : ViewModel() {
    private val reposRepository: ReposRepository = ReposRepository();

    val reposPages by lazy {
        reposRepository.getPagedList()
    }

    fun searchRepos(name: String){
        reposRepository.searchRepos(name);
    }

    fun addToFavorite(item: ReposResponseItem){
        reposRepository.addToFavorite(item)
    }
}