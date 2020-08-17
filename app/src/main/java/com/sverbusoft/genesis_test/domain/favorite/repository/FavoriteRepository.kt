package com.sverbusoft.genesis_test.domain.favorite.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sverbusoft.genesis_test.domain.repos.model.ReposModel

interface FavoriteRepository {
    fun searchRepos(name: String)

    fun deleteRepos(item: ReposModel)

    fun getPagedList(): LiveData<PagedList<ReposModel>>
}