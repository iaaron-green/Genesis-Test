package com.sverbusoft.genesis_test.data.features.favorite.datasource.paging

import androidx.paging.PositionalDataSource
import com.sverbusoft.genesis_test.data.features.favorite.datasource.local.FavoriteDao
import com.sverbusoft.genesis_test.data.features.repos.dto.ReposResponseItem
import com.sverbusoft.genesis_test.data.features.repos.mapper.ReposModelToEntityMapper
import com.sverbusoft.genesis_test.data.features.repos.model.ReposModel

class FavoritePageDataSource(
    private val dataSource: FavoriteDao,
    var name: String
) : PositionalDataSource<ReposModel>() {

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<ReposModel>
    ) {
        val result = ReposModelToEntityMapper().mapFromObjects(
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
        callback: LoadInitialCallback<ReposModel>
    ) {
        val result = ReposModelToEntityMapper().mapFromObjects(
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