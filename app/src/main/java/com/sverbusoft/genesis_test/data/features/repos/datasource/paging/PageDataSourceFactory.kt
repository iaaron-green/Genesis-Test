package com.example.swivltestassigment.data.datasource.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.swivltestassigment.data.datasource.remote.UserApiDataSource
import com.example.swivltestassigment.data.model.UserModel

class PageDataSourceFactory(
    private val dataSource: UserApiDataSource
) : DataSource.Factory<Int, UserModel>() {

    override fun create(): DataSource<Int, UserModel> {
        val source = UserPageDataSource(dataSource)
        return source;
    }

    companion object {
        private const val PAGE_SIZE = 50

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
    }
}