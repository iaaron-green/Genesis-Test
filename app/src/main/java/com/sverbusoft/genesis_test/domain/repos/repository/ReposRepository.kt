package com.sverbusoft.genesis_test.domain.repos.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sverbusoft.genesis_test.domain.repos.model.ReposModel

interface ReposRepository {
    fun searchRepos(name: String);
    fun getPagedList(): LiveData<PagedList<ReposModel>>;
    fun addToFavorite(item: ReposModel);

}