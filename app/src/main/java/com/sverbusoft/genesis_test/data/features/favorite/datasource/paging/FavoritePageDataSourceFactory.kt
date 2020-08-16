package com.sverbusoft.genesis_test.data.features.favorite.datasource.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.sverbusoft.genesis_test.data.features.favorite.datasource.local.FavoriteDao
import com.sverbusoft.genesis_test.data.features.repos.model.ReposModel

class FavoritePageDataSourceFactory(
    private val dataSource: FavoriteDao,
    internal var name: String
) : DataSource.Factory<Int, ReposModel>() {

    val usersDataSourceLiveData = MutableLiveData<FavoritePageDataSource>()

    override fun create(): DataSource<Int, ReposModel> {
        val source = FavoritePageDataSource(dataSource, name)
        usersDataSourceLiveData.postValue(source)
        return source;
    }

    companion object {
        private const val PAGE_SIZE = 15

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
    }

    fun refresh() {
        usersDataSourceLiveData.value?.invalidate()
    }
}