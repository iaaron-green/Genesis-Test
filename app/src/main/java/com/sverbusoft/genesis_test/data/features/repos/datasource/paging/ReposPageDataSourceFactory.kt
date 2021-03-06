package com.sverbusoft.genesis_test.data.features.repos.datasource.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.sverbusoft.genesis_test.data.features.repos.datasource.local.ReposDao
import com.sverbusoft.genesis_test.data.features.repos.datasource.remote.ReposRemoteDataSource
import com.sverbusoft.genesis_test.domain.repos.model.ReposModel
import io.reactivex.disposables.CompositeDisposable

class ReposPageDataSourceFactory(
    private val remoteDataSource: ReposRemoteDataSource,
    private val localDataSource: ReposDao,
    internal var name: String,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, ReposModel>() {

    val usersDataSourceLiveData = MutableLiveData<ReposPageDataSource>()

    override fun create(): DataSource<Int, ReposModel> {
        val source = ReposPageDataSource(remoteDataSource, localDataSource, name, compositeDisposable)
        usersDataSourceLiveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 30

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
    }

    fun refresh() {
        compositeDisposable.clear()
        usersDataSourceLiveData.value?.invalidate()
    }
}