package com.sverbusoft.genesis_test.ui.fragments.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sverbusoft.genesis_test.data.features.repos.repository.ReposRepository

class ReposViewModel : ViewModel() {
    private val reposRepository: ReposRepository = ReposRepository();

    val reposPages by lazy {
        reposRepository.searchRepos("qwe")
    }
}