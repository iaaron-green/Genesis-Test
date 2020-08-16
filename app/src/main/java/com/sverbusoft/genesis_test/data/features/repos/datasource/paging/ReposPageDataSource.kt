package com.sverbusoft.genesis_test.data.features.repos.datasource.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.sverbusoft.genesis_test.data.features.repos.datasource.remote.ReposRemoteDataSource
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ReposPageDataSource(
    private val dataSource: ReposRemoteDataSource,
    var name: String
) : PageKeyedDataSource<Int, ReposResponseItem>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ReposResponseItem>
    ) {
        Log.d("MyTag loadInitial", "params: " + 0 + " " + params.requestedLoadSize + " ")
        fetchData(name, 1, params.requestedLoadSize) {
            callback.onResult(it, null, 3)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ReposResponseItem>
    ) {
        Log.d("MyTag loadAfter", "params: " + params.key + " " + params.requestedLoadSize + " ")
        fetchData(name, params.key, params.requestedLoadSize) {
            callback.onResult(it, params.key + 2)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ReposResponseItem>
    ) {
        Log.d("MyTag loadBefore", "params: " + params.key + " " + params.requestedLoadSize + " ")
        fetchData(name, params.key, params.requestedLoadSize) {
            callback.onResult(it, params.key - 2)
        }
    }

    private fun fetchData(name: String, page: Int, pageSize: Int, callback: (List<ReposResponseItem>) -> Unit) {
        if(name.isEmpty()) {
            callback(listOf())
            return
        }
        var disposable = Single.concat(
            dataSource.getUserRepos(name, page, pageSize),
            dataSource.getUserRepos(name, page + 1, pageSize))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback(it)
            }, {
                it.printStackTrace()
            })

    }
}