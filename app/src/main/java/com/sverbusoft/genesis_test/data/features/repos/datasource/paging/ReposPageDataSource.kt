package com.sverbusoft.genesis_test.data.features.repos.datasource.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.sverbusoft.genesis_test.data.features.repos.datasource.local.ReposDao
import com.sverbusoft.genesis_test.data.features.repos.datasource.remote.ReposRemoteDataSource
import com.sverbusoft.genesis_test.data.features.repos.mapper.ReposDtoToDomainMapper
import com.sverbusoft.genesis_test.domain.repos.model.ReposModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ReposPageDataSource(
    private val remoteDataSource: ReposRemoteDataSource,
    private val localDataSource: ReposDao,
    var name: String,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, ReposModel>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ReposModel>
    ) {
        Log.d("MyTag loadInitial", "params: " + 0 + " " + params.requestedLoadSize + " ")
        fetchData(name, 1, params.requestedLoadSize) {
            callback.onResult(it, null, 3)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ReposModel>
    ) {
        Log.d("MyTag loadAfter", "params: " + params.key + " " + params.requestedLoadSize + " ")
        fetchData(name, params.key, params.requestedLoadSize) {
            callback.onResult(it, params.key + 2)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ReposModel>
    ) {
        Log.d("MyTag loadBefore", "params: " + params.key + " " + params.requestedLoadSize + " ")
        fetchData(name, params.key, params.requestedLoadSize) {
            callback.onResult(it, params.key - 2)
        }
    }

    private fun fetchData(
        name: String,
        page: Int,
        pageSize: Int,
        callback: (List<ReposModel>) -> Unit
    ) {
        if (name.isEmpty()) {
            callback(listOf())
            return
        }

        var temp = mutableListOf<ReposModel>()
        var firstResult: Boolean = false;
        var secondResult: Boolean = false;

        var resultFirstDisposable = remoteDataSource.getRepos(name, page, pageSize / 2)
            .subscribeOn(Schedulers.io())
            .map { ReposDtoToDomainMapper().mapFromObjects(it) }
            .map { t ->
                t.forEach { s: ReposModel ->
                    run {
                        if (localDataSource.getFavoriteRepos(s.id) != null) s.favorite = true
                    }
                }
                return@map t
            }
            .subscribe({
                firstResult = true;
                if (secondResult) {
                    callback(mutableListOf<ReposModel>().apply {
                        addAll(it)
                        addAll(temp);
                    })
                    temp.clear()
                } else {
                    temp = mutableListOf<ReposModel>().apply {
                        addAll(it);
                    }
                }
            }, {
                it.printStackTrace()
            })

        var resultSecondDisposable = remoteDataSource.getRepos(name, page + 1, pageSize / 2)
            .subscribeOn(Schedulers.io())
            .map { ReposDtoToDomainMapper().mapFromObjects(it) }
            .map { t ->
                t.forEach { s: ReposModel ->
                    run {
                        if (localDataSource.getFavoriteRepos(s.id) != null) s.favorite = true
                    }
                }
                return@map t
            }
            .subscribe({
                secondResult = true;

                if (firstResult) {
                    callback(mutableListOf<ReposModel>().apply {
                        addAll(temp)
                        addAll(it);
                    })
                    temp.clear()
                } else {
                    temp = mutableListOf<ReposModel>().apply {
                        addAll(it);
                    }
                }
            }, { it.printStackTrace() })

        compositeDisposable.add(resultFirstDisposable)
        compositeDisposable.add(resultSecondDisposable)
    }
}