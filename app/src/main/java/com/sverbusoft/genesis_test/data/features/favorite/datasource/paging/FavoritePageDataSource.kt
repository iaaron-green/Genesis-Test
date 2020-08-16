package com.sverbusoft.genesis_test.data.features.favorite.datasource.paging

import androidx.paging.PositionalDataSource
import com.sverbusoft.genesis_test.data.features.favorite.datasource.local.FavoriteDao
import com.sverbusoft.genesis_test.data.features.repos.mapper.ReposModelToEntityMapper
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem

class FavoritePageDataSource(
    private val dataSource: FavoriteDao,
    var name: String
) : PositionalDataSource<ReposResponseItem>() {
//    override fun loadInitial(
//        params: LoadInitialParams<Int>,
//        callback: LoadInitialCallback<Int, ReposResponseItem>
//    ) {
//        Log.d("MyTag loadInitial", "params: " + 0 + " " + params.requestedLoadSize + " ")
//        fetchData(name, 1, params.requestedLoadSize) {
//            callback.onResult(it, null, 3)
//        }
//    }
//
//    override fun loadAfter(
//        params: LoadParams<Int>,
//        callback: LoadCallback<Int, ReposResponseItem>
//    ) {
//        Log.d("MyTag loadAfter", "params: " + params.key + " " + params.requestedLoadSize + " ")
//        fetchData(name, params.key, params.requestedLoadSize) {
//            callback.onResult(it, params.key + 2)
//        }
//    }
//
//    override fun loadBefore(
//        params: LoadParams<Int>,
//        callback: LoadCallback<Int, ReposResponseItem>
//    ) {
//        Log.d("MyTag loadBefore", "params: " + params.key + " " + params.requestedLoadSize + " ")
//        fetchData(name, params.key, params.requestedLoadSize) {
//            callback.onResult(it, params.key - 2)
//        }
//    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<ReposResponseItem>
    ) {
        var result = ReposModelToEntityMapper().mapFromObjects(
            dataSource.getFavoriteRepos(
                params.startPosition,
                params.loadSize,
                "%$name%"
            )
        )
        callback.onResult(result)
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<ReposResponseItem>
    ) {
        var result = ReposModelToEntityMapper().mapFromObjects(
            dataSource.getFavoriteRepos(
                params.requestedStartPosition,
                params.requestedLoadSize,
                "%$name%"
            )
        )
        callback.onResult(result, 0)
    }

    private fun fetchData(
        name: String,
        start: Int,
        size: Int,
        callback: (List<ReposResponseItem>) -> Unit
    ) {
        if (name.isEmpty()) {
            callback(listOf())
            return
        }

    }
}