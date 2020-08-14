package com.sverbusoft.genesis_test.data.features.repos.datasource.paging

import androidx.paging.PageKeyedDataSource
import com.sverbusoft.genesis_test.data.features.repos.datasource.remote.ReposRemoteDataSource
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ReposPageDataSource(
    private val dataSource: ReposRemoteDataSource
) : PageKeyedDataSource<Int, ReposResponseItem>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ReposResponseItem>
    ) {
        fetchData(0, params.requestedLoadSize) {
            callback.onResult(it, null, 1)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ReposResponseItem>
    ) {
        fetchData(params.key, params.requestedLoadSize) {
            callback.onResult(it, params.key + 1)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ReposResponseItem>
    ) {
        fetchData(params.key, params.requestedLoadSize) {
            callback.onResult(it, params.key - 1)
        }
    }

    private fun fetchData(page: Int, pageSize: Int, callback: (List<ReposResponseItem>) -> Unit) {
        var disposable = dataSource.getUserRepos("qwe")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback(it)
            }, {
                it.printStackTrace()
            })
        ;
    }
}