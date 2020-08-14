package com.sverbusoft.genesis_test.data.features.repos.datasource.paging

import androidx.paging.PositionalDataSource
import com.sverbusoft.genesis_test.data.features.repos.datasource.remote.ReposRemoteDataSource
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ReposPageDataSource(
    private val dataSource: ReposRemoteDataSource
) : PositionalDataSource<ReposResponseItem>() {
    var id: Int = 0;

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<ReposResponseItem>
    ) {
        fetchData(id) {
            id = it[it.lastIndex].id
            callback.onResult(it, 0)
        }
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<ReposResponseItem>
    ) {
        fetchData(id) {
            id = it[it.lastIndex].id
            callback.onResult(it)
        }
    }

    private fun fetchData(pageSize: Int, callback: (List<ReposResponseItem>) -> Unit) {
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