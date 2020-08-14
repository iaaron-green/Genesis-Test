package com.sverbusoft.genesis_test.data.features.repos.datasource

import com.sverbusoft.genesis_test.data.features.repos.api.ReposApi
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem
import io.reactivex.Single

class ReposRemoteDataSource constructor(private val api: ReposApi){
    fun getUserRepos(name: String): Single<List<ReposResponseItem>> =
        api.search(name)
}