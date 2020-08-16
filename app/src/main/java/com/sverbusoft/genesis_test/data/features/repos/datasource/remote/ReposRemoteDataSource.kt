package com.sverbusoft.genesis_test.data.features.repos.datasource.remote

import com.sverbusoft.genesis_test.data.features.repos.api.ReposApi
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResult
import io.reactivex.Single

class ReposRemoteDataSource constructor(private val api: ReposApi){
    fun getUserRepos(name: String, page: Int, per_page: Int): Single<List<ReposResponseItem>> =
        api.search(name, page, per_page).map { t: ReposResult -> t.items }
}