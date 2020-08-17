package com.sverbusoft.genesis_test.data.features.favorite.datasource.paging

import androidx.paging.PositionalDataSource
import com.sverbusoft.genesis_test.data.features.favorite.datasource.local.FavoriteDao
import com.sverbusoft.genesis_test.data.features.repos.dto.ReposResponseItem
import com.sverbusoft.genesis_test.data.features.repos.mapper.ReposModelToEntityMapper
import com.sverbusoft.genesis_test.domain.repos.model.ReposModel

class FavoritePageDataSource(
    private val dataSource: FavoriteDao,
    var name: String
) : PositionalDataSource<ReposModel>() {

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<ReposModel>
    ) {
        fetchData(params.startPosition, params.loadSize){
            callback.onResult(it)
        }
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<ReposModel>
    ) {
        fetchData(params.requestedStartPosition, params.requestedLoadSize){
            callback.onResult(it, 0)
        }
    }

    private fun fetchData(
        start: Int,
        size: Int,
        callback: (List<ReposModel>) -> Unit
    ) {
        val result = ReposModelToEntityMapper().mapFromObjects(
            dataSource.getFavoriteRepos(
                start,
                size,
                "%$name%"
            )
        )
        callback(result)
    }
}