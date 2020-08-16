package com.sverbusoft.genesis_test.data.features.favorite.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sverbusoft.genesis_test.data.core.db.AppDatabase
import com.sverbusoft.genesis_test.data.features.favorite.datasource.local.FavoriteDao
import com.sverbusoft.genesis_test.data.features.favorite.datasource.paging.FavoritePageDataSourceFactory
import com.sverbusoft.genesis_test.data.features.repos.datasource.local.ReposDao
import com.sverbusoft.genesis_test.data.features.repos.datasource.paging.ReposPageDataSourceFactory
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem

class FavoriteRepository {
    private var localDataSource: FavoriteDao = AppDatabase.getInstance()!!.favoriteDao();
    private var dataSourceFactory: FavoritePageDataSourceFactory;

    init {
        dataSourceFactory = FavoritePageDataSourceFactory(localDataSource, "")
    }

    fun searchRepos(name: String) {
        Log.d("MyTag searchRepos", "name: $name")
        dataSourceFactory.name = name
        dataSourceFactory.refresh()
    }

    fun getPagedList(): LiveData<PagedList<ReposResponseItem>> {
        return LivePagedListBuilder(
            dataSourceFactory,
            ReposPageDataSourceFactory.pagedListConfig()
        ).build()
    }
}